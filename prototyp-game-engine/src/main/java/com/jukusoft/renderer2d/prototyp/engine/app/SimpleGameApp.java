package com.jukusoft.renderer2d.prototyp.engine.app;

import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWUtils;
import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWWindow;
import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import com.jukusoft.renderer2d.prototyp.engine.window.callback.AbstractKeyCallback;
import org.apache.log4j.Logger;

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
        this.window = new GLFWWindow(600, 400, "Window title");
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
    }

    public void shutdown () {
        //
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
