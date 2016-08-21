package com.jukusoft.renderer2d.prototyp;

import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWUtils;
import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWWindow;
import com.jukusoft.renderer2d.prototyp.engine.window.callback.AbstractKeyCallback;
import com.jukusoft.renderer2d.prototyp.engine.window.callback.KeyCallback;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Created by Justin on 21.08.2016.
 */
public class Main {

    public static void main (String[] args) {
        //configure log4j to log to console
        BasicConfigurator.configure();

        //log message
        Logger.getRootLogger().info("initialize GLFW now.");

        //initialize GLFW
        GLFWUtils.init();

        //create new GLFW window
        IWindow window = new GLFWWindow(600, 400, "Window title");
        window.create();

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

        //TODO: add code here

        try {
            //wait 5 seconds
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //close window
        window.destroy();

        //log message
        Logger.getRootLogger().info("shutdown GLFW now.");

        //shutdown GLFW
        GLFWUtils.shutdownGLFW();
    }

}
