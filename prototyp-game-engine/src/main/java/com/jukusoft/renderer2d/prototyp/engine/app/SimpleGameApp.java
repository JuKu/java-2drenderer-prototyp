package com.jukusoft.renderer2d.prototyp.engine.app;

import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWUtils;
import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWWindow;
import com.jukusoft.renderer2d.prototyp.engine.utils.GamePlatform;
import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import com.jukusoft.renderer2d.prototyp.engine.window.callback.AbstractKeyCallback;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

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
    * default constructor
     *
     * @param useMultiThreading true, if updates should be executed in extra update thread
    */
    public SimpleGameApp (boolean useMultiThreading) {
        this.useMultiThreading = useMultiThreading;
    }

    /**
     * default constructor
     */
    public SimpleGameApp () {
        this.useMultiThreading = true;
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
        }

        Logger.getRootLogger().info("window was closed by user.");

        this.shutdown();
    }

    protected void createUpdateThread () {
        Thread updateThread = new Thread(() -> {
            //start gameloop
            while (!exitFlag.get()) {
                //update game
                update(1);

                //execute tasks which should be executed in update thread
                GamePlatform.executeUpdateQueue();
            }
        });
    }

    public void shutdown () {
        //close window
        this.window.close();

        //log message
        Logger.getRootLogger().info("shutdown GLFW now.");

        //shutdown GLFW
        GLFWUtils.shutdownGLFW();
    }

    public IWindow getWindow () {
        return this.window;
    }

    protected abstract void onCreateWindow (IWindow window);

    /**
    * update game state
    */
    public abstract void update (double delta);

    /**
    * render window
    */
    public abstract void render ();

}
