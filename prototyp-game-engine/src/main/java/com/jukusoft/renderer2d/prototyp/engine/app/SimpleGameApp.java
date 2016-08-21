package com.jukusoft.renderer2d.prototyp.engine.app;

import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWUtils;
import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWWindow;
import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import com.jukusoft.renderer2d.prototyp.engine.window.callback.AbstractKeyCallback;
import org.apache.log4j.Logger;

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
    * default constructor
    */
    public SimpleGameApp () {
        //
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

        //renderer loop
        while (!window.shouldClose()) {
            //clear framebuffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            //swap back and front buffers
            window.swap();

            //process input events and call callbacks
            window.processInput();
        }

        Logger.getRootLogger().info("window was closed by user.");

        this.shutdown();
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
    public abstract void update ();

    /**
    * render window
    */
    public abstract void render ();

}
