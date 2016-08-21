package com.jukusoft.renderer2d.prototyp.engine.glfw;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import com.jukusoft.renderer2d.prototyp.engine.utils.ImageUtils;
import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import com.jukusoft.renderer2d.prototyp.engine.window.callback.KeyCallback;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Justin on 21.08.2016.
 */
public class GLFWWindow implements IWindow {

    protected int width = 600;
    protected int height = 400;
    protected String title = "";

    protected int minWidth = GLFW_DONT_CARE;
    protected int maxWidth = GLFW_DONT_CARE;
    protected int minHeight = GLFW_DONT_CARE;
    protected int maxHeight = GLFW_DONT_CARE;

    /**
    * GLFW window id
    */
    protected long window = 0l;

    /**
    * GLFW monitor id on which window is shown
    */
    protected long monitor = 0l;

    /**
    * key callback to process key events
    */
    protected GLFWKeyCallback keyCallback = null;

    /**
    * list with key callbacks
    */
    protected List<KeyCallback> keyCallbackList = new ArrayList<>();

    /**
    * true, if application should exit if window close button was pressed
    */
    protected AtomicBoolean exitOnClose = new AtomicBoolean(false);

    public GLFWWindow (int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    @Override
    public void create () {
        //create new window

        //configure GLFW window - optional, the current window hints are already the default
        glfwDefaultWindowHints();

        //hide window after creation
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);

        //set window resizeable
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        //set monitor to primary monitor, if monitor != NULL window will be shown in fullscreen mode
        this.monitor = NULL;//glfwGetPrimaryMonitor();

        //create GLFW window
        this.window = glfwCreateWindow(this.width, this.width, this.title, this.monitor, NULL);

        //check, if error occours while creating window
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        //setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(this.window, this.keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, true); //we will detect this in our rendering loop

                    if (exitOnClose.get()) {
                        //close and destroy window
                        GLFWWindow.this.close();

                        //cleanup GLFW
                        GLFWUtils.shutdownGLFW();

                        //exit
                        System.exit(0);
                    }
                }

                //call key callbacks
                callKeyCallbacks(window, key, scancode, action, mods);
            }
        });

        //getresolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        //center window
        glfwSetWindowPos(
                window,
                (vidmode.width() - this.width) / 2,
                (vidmode.height() - this.height) / 2
        );

        //make the OpenGL context current
        glfwMakeContextCurrent(this.window);

        // Enable v-sync
        glfwSwapInterval(1);
    }

    /**
    * set visibility of window
     *
     * @param visible visibility of window
    */
    @Override
    public void setVisible (boolean visible) {
        if (visible) {
            //make the window visible
            glfwShowWindow(this.window);
        } else {
            //hide window
            glfwHideWindow(this.window);
        }
    }

    /**
    * set window title
     *
     * @param title title of window
    */
    @Override
    public void setTitle (String title) {
        //set window title
        glfwSetWindowTitle(this.window, title);

        this.title = title;
    }

    /**
    * set window size
     *
     * @param width width of window
     * @param height height of window
    */
    @Override
    public void setSize (int width, int height) {
        //set window size
        glfwSetWindowSize(this.window, width, height);
    }

    @Override
    public void setMinSize(int width, int height) {
        this.minWidth = width;
        this.minHeight = height;

        this.updateMinAndMaxSize();
    }

    @Override
    public void setMaxSize(int width, int height) {
        this.maxWidth = width;
        this.maxHeight = height;

        this.updateMinAndMaxSize();
    }

    /**
    * update minimum and maximum window size
    */
    private void updateMinAndMaxSize () {
        glfwSetWindowSizeLimits(this.window, this.minWidth, this.minHeight, this.maxWidth, this.maxHeight);
    }

    /**
    * get GLFW id of monitor on which window is shown
     *
     * @return GLFW id of monitor
    */
    public long getMonitorID () {
        return glfwGetWindowMonitor(this.window);
    }

    /**
    * get GLFW id of window
     *
     * @return GLFW id of window
    */
    public long getWindowID () {
        return this.window;
    }

    @Override
    public void setPosition(int x, int y) {
        //set window position
        glfwSetWindowPos(this.window, x, y);
    }

    @Override
    public void setResizeable(boolean resizeable) {
        throw new UnsupportedOperationException("This method isnt implemented yet.");
    }

    @Override
    public void setIcon(String imagePath) throws IOException {
        throw new UnsupportedOperationException("This method isnt implemented yet.");

        /*BufferedImage iconImageBuffer = ImageIO.read(new File(imagePath));
        ByteBuffer imageBuffer = ImageUtils.convertImageToByteBuffer(iconImageBuffer);
        glfwSetWindowIcon(this.window, imageBuffer);*/
    }

    @Override
    public void setDefaultIcon() {
        throw new UnsupportedOperationException("This method isnt implemented yet.");
        //glfwSetWindowIcon(this.window, NULL);
    }

    @Override
    public void addKeyCallback(KeyCallback callback) {
        //add callback to list
        Collections.synchronizedCollection(this.keyCallbackList).add(callback);

        //sort list
        Collections.sort(Collections.synchronizedList(this.keyCallbackList));
    }

    @Override
    public void removeKeyCallback(KeyCallback callback) {
        //remove callback from list
        Collections.synchronizedCollection(this.keyCallbackList).remove(callback);
    }

    @Override
    public void prepareRendering() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.

        //see https://github.com/lwjglgamedev/lwjglbook/blob/master/chapter01/src/main/java/org/lwjglb/game/Main.java
        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(this.window);
    }

    @Override
    public void setExitOnClose(boolean exitOnClose) {
        this.exitOnClose.set(exitOnClose);
    }

    @Override
    public void swap() {
        //swap back and front buffer
        glfwSwapBuffers(this.window);
    }

    protected void callKeyCallbacks (long window, int key, int scancode, int action, int mods) {
        //check, if key was pressed
        if (action == GLFW_PRESS) {
            //iterate through key callbacks
            for (KeyCallback callback : this.keyCallbackList) {
                //call key callback, if return value if false, dont execute other key callbacks
                if (!callback.keyPressed(key)) {
                    break;
                }
            }
        } else if (action == GLFW_RELEASE) {
            //iterate through key callbacks
            for (KeyCallback callback : this.keyCallbackList) {
                //call key callback, if return value if false, dont execute other key callbacks
                if (!callback.keyPressed(key)) {
                    break;
                }
            }
        }
    }

    @Override
    public void close() {
        //TODO: call close listeners

        this.destroy();
    }

    @Override
    public void destroy() {
        //release window and window callbacks
        glfwDestroyWindow(window);
    }

}
