package com.jukusoft.renderer2d.prototyp;

import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWUtils;
import com.jukusoft.renderer2d.prototyp.engine.glfw.GLFWWindow;
import com.jukusoft.renderer2d.prototyp.engine.window.callback.AbstractKeyCallback;
import com.jukusoft.renderer2d.prototyp.engine.window.callback.KeyCallback;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengles.GLES20.GL_COLOR_BUFFER_BIT;

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

        //renderer loop
        while (!window.shouldClose()) {
            //clear framebuffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            //swap back and front buffers
            window.swap();

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }

        //close window
        window.destroy();

        //log message
        Logger.getRootLogger().info("shutdown GLFW now.");

        //shutdown GLFW
        GLFWUtils.shutdownGLFW();
    }

}
