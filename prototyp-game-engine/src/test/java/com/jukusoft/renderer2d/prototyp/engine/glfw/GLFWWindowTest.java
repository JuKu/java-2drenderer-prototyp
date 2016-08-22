package com.jukusoft.renderer2d.prototyp.engine.glfw;

import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

/**
 * Created by Justin on 22.08.2016.
 */
public class GLFWWindowTest {

    @Test(timeout = 2000)
    public void testInitWindow () {
        //configure log4j
        BasicConfigurator.configure();

        //initialize GLFW
        GLFWUtils.init();

        //create new window
        IWindow window = new GLFWWindow(600, 400, "window title", false);

        //create window
        window.create();

        //destroy window
        window.destroy();

        //shutdown GLFW
        GLFWUtils.shutdownGLFW();
    }

    @Test(expected = RuntimeException.class)
    public void testForRuntimeException () {
        //create new window
        IWindow window = new GLFWWindow(600, 400, "window title", false);

        //create window
        window.create();

        //destroy window
        window.destroy();
    }

}
