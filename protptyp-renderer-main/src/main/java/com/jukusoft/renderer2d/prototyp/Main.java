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

        //create new instance of game with not fixed fps rate, but 60 updates per second
        MySimpleGameStateApp app = new MySimpleGameStateApp(true, -1, 60, false);

        //initialize game
        app.init();

        //start game
        app.start();
    }

}
