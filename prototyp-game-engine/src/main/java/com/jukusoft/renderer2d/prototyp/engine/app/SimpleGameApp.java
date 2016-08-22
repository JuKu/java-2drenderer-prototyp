package com.jukusoft.renderer2d.prototyp.engine.app;

import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWUtils;
import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWWindow;
import com.jukusoft.renderer2d.prototyp.engine.utils.GamePlatform;
import com.jukusoft.renderer2d.prototyp.engine.utils.Timer;
import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import com.jukusoft.renderer2d.prototyp.engine.window.callback.AbstractKeyCallback;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengles.GLES20.GL_COLOR_BUFFER_BIT;

/**
 * Created by Justin on 21.08.2016.
 */
public abstract class SimpleGameApp {

    /**
    * window
    */
    protected IWindow window = null;

    /**
    * flag, if multi threading should be used and
    */
    protected final boolean useMultiThreading;

    /**
    * exit flag
    */
    protected AtomicBoolean exitFlag = new AtomicBoolean(false);

    /**
    * fixed frames per second rate flag
     *
     * if true, game application will not execute render method more than fixed fps
    */
    protected AtomicBoolean fixedFPS = new AtomicBoolean(false);

    /**
     * frames per second (fps) count
     */
    protected AtomicLong fps = new AtomicLong(1);

    /**
     * timestamp of last render execution
     */
    protected AtomicLong lastRendererTimestamp = new AtomicLong(1);

    /**
     * calculate updates per second and call call update() method near this rate
     */
    protected final AtomicBoolean useUPS = new AtomicBoolean(true);

    /**
     * target frames per second rate
     *
     * by default 60 frames per second
     */
    protected final AtomicInteger targetFPS = new AtomicInteger(60);

    /**
    * target updates per second rate
     *
     * by default 60 updates per second
    */
    protected final AtomicInteger targetUPS = new AtomicInteger(60);

    /**
    * updates per second timer
    */
    protected final Timer upsTimer = new Timer();

    /**
    * last update per second counter
    */
    protected final AtomicInteger lastUPSCounter = new AtomicInteger(0);

    /**
    * the update keep up warning message
    */
    private static final String UPDATE_KEEP_UP_WARNING =
            "Can't keep up with update rate, maybe it's to high or other tasks are slowing down the execution.";

    /**
    * default constructor
     *
     * @param useMultiThreading true, if updates should be executed in extra update thread
    */
    public SimpleGameApp (boolean useMultiThreading, int targetFPS, int targetUPS) {
        this.useMultiThreading = useMultiThreading;

        if (targetFPS < 1) {
            this.fixedFPS.set(false);
        } else {
            this.fixedFPS.set(true);
            this.targetFPS.set(targetFPS);
        }

        if (targetUPS < 1) {
            this.useUPS.set(false);
        } else {
            this.useUPS.set(true);
            this.targetUPS.set(targetUPS);
        }

        if (!this.useMultiThreading && this.useUPS.get()) {
            throw new IllegalArgumentException("fixed updates per second is only available if multi threading is enabled.");
        }
    }

    /**
     * default constructor
     */
    public SimpleGameApp () {
        this.useMultiThreading = true;
        this.fixedFPS.set(true);
        this.targetFPS.set(60);
    }

    public void init () {
        //log message
        Logger.getRootLogger().info("initialize GLFW now.");

        //initialize GLFW
        GLFWUtils.init();
    }

    public void start () {
        //create new GLFW window
        this.window = new GLFWWindow(600, 400, "Simple Game App");
        this.window.create();

        this.onCreateWindow(window);

        //set window title
        window.setTitle("Window Title");

        //set key callback
        window.addKeyCallback(new AbstractKeyCallback() {
            @Override
            public boolean keyPressed(int key) {
                Logger.getRootLogger().info("key pressed.");

                //execute other callbacks
                return true;
            }

            @Override
            public boolean keyReleased(int key) {
                Logger.getRootLogger().info("key released.");

                //execute other callbacks
                return true;
            }
        });

        //show window
        window.setVisible(true);

        //prepare rendering and create GL capabilities like GL.createCapabilities()
        window.prepareRendering();

        //initialize game
        this.initialize();

        //start renderer and gameloop
        if (!this.useMultiThreading) {
            Logger.getRootLogger().info("multi threading for game engine isnt enabled, use only one thread to update and render game.");

            //renderer loop
            while (!window.shouldClose()) {
                //process input events and call callbacks
                window.processInput();

                //update game state
                this.update(1);

                //execute tasks which should be executed in update thread
                GamePlatform.executeUpdateQueue();

                //clear framebuffer
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                //render
                this.render();

                //swap back and front buffers
                window.swap();

                //execute tasks which should be executed in ui thread
                GamePlatform.executeUIQueue();
            }
        } else {
            Logger.getRootLogger().info("multi threading for game engine is enabled, create new thread for gameloop.");

            //create new thread for updates
            this.createUpdateThread();

            //renderer loop
            while (!window.shouldClose()) {
                //process input events and call callbacks
                window.processInput();

                //clear framebuffer
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                //render
                this.render();

                //swap back and front buffers
                window.swap();

                //execute tasks which should be executed in ui thread
                GamePlatform.executeUIQueue();
            }

            //set exit flag, if it wasnt set before
            this.exitFlag.set(true);
        }

        Logger.getRootLogger().info("window was closed by user.");

        //shutdown game engine
        this.shutdown();
    }

    protected void createUpdateThread () {
        //create new update thread
        Thread updateThread = new Thread(() -> {
            /**
             * for updates per second execution
             */
            //float lastUpdateTime = System.nanoTime();
            //float interval = 1f / this.targetUPS.get();
            //float accumulator = 0f;

            //double accumulator = 0.0;
            //double updateDelta = (1.0 / this.targetUPS.get()) * 1_000_000_000;

            double lastExecution = System.nanoTime();
            double lastExecutionInSeconds = lastExecution / 1000_000_000d;

            double targetElapsedTime = 1.0 / this.targetUPS.get();

            //1000_000_000ns = 1 second

            double waitTimeOverflow = 0d;

            int lastSecond = 0;
            int currentSecond = 0;
            int upsCounter = 0;

            //start gameloop
            while (!exitFlag.get() && !Thread.currentThread().isInterrupted()) {
                if (this.useUPS.get()) {
                    double currentTime = System.nanoTime();
                    double elapsed = currentTime - lastExecution;
                    lastExecution = currentTime;

                    double elapsedTimeInSeconds = elapsed / 1000000000d;

                    currentSecond = (int) System.currentTimeMillis() / 1000;

                    //check, if its the same second
                    if (currentSecond != lastSecond) {
                        //save ups
                        this.lastUPSCounter.set(upsCounter);

                        //set new last second
                        lastSecond = currentSecond;

                        //log ups counter
                        Logger.getRootLogger().debug("UPS: " + this.lastUPSCounter.get());

                        //reset counter
                        upsCounter = 0;
                    }

                    //increment updates per second counter
                    upsCounter++;

                    /*accumulator += elapsed;

                    while (accumulator >= interval) {
                        updateGame(interval);
                        accumulator -= interval;
                    }*/

                    //Logger.getRootLogger().debug("elapsed: " + elapsedTimeInSeconds + "ns, targetElapsedTime: " + targetElapsedTime + ", wait time: " + (targetElapsedTime - elapsedTimeInSeconds));

                    updateGame(this.targetUPS.get());

                    /*while (currentTime + interval > System.nanoTime()) {
                        Logger.getRootLogger().debug("wait");

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }*/

                    double waitTimeInNs = targetElapsedTime - elapsedTimeInSeconds;
                    int waitTimeInMs = (int) Math.round((waitTimeInNs + waitTimeOverflow) / 1_000_000);

                    waitTimeOverflow = waitTimeInNs - Math.round(waitTimeInNs / 1_000_000) * 1_000_000;

                    /*try {
                        //Logger.getRootLogger().debug("wait " + waitTimeInMs + "ms, " + waitTimeInNs + "ns, overflow: " + waitTimeOverflow);

                        Thread.sleep(waitTimeInMs);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                } else {
                    updateGame(1);
                }
            }

            Logger.getRootLogger().info("shutdown gameloop now.");
        });

        //log message
        Logger.getRootLogger().info("start update thread now.");

        //start thread
        updateThread.start();
    }

    private final void updateGame (double delta) {
        //Logger.getRootLogger().debug("update.");

        //update game
        update(delta);

        //execute tasks which should be executed in update thread
        GamePlatform.executeUpdateQueue();
    }

    public void shutdown () {
        //set exit flag
        this.exitFlag.set(true);

        //call hook
        this.beforeShutdown();

        //close window
        this.window.close();

        //log message
        Logger.getRootLogger().info("shutdown GLFW now.");

        //shutdown GLFW
        GLFWUtils.shutdownGLFW();

        //call hook
        this.afterShutdown();
    }

    protected void beforeShutdown () {
        //
    }

    protected void afterShutdown () {
        //
    }

    public IWindow getWindow () {
        return this.window;
    }

    public boolean wasResized () {
        return this.getWindow().wasResized();
    }

    public void setResizedFlag (boolean resized) {
        this.getWindow().setResizedFlag(resized);
    }

    /**
    * get frames per second rate
     *
     * @return frames per second rate
    */
    public long getFPS () {
        return this.fps.get();
    }

    /**
    * get updates per second rate
     *
     * @return updates per second rate
    */
    public int getUPS () {
        return this.lastUPSCounter.get();
    }

    protected abstract void onCreateWindow (IWindow window);

    /**
    * will be called if app is initializing game
    */
    protected abstract void initialize();

    /**
    * update game state
    */
    public abstract void update (double delta);

    /**
    * render window
    */
    public abstract void render ();

}
