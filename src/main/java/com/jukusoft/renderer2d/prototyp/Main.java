package com.jukusoft.renderer2d.prototyp;

import com.jukusoft.renderer2d.prototyp.glfw.GLFWUtils;
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

        //TODO: add code here

        //log message
        Logger.getRootLogger().info("shutdown GLFW now.");

        //shutdown GLFW
        GLFWUtils.shutdownGLFW();
    }

}
