package com.jukusoft.renderer2d.prototyp;

import com.jukusoft.renderer2d.prototyp.glfw.GLFWUtils;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by Justin on 21.08.2016.
 */
public class Main {

    public static void main (String[] args) {
        //configure log4j to log to console
        BasicConfigurator.configure();

        //initialize GLFW
        GLFWUtils.init();

        //TODO: add code here

        //shutdown GLFW
        GLFWUtils.shutdownGLFW();
    }

}
