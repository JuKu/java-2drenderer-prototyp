package com.jukusoft.renderer2d.prototyp.engine.window;

import com.jukusoft.renderer2d.prototyp.engine.window.callback.KeyCallback;

import java.io.IOException;

/**
 * Created by Justin on 21.08.2016.
 */
public interface IWindow {

    /**
    * create window
    */
    public void create ();

    /**
     * set visibility of window
     *
     * @param visible visibility of window
     */
    public void setVisible (boolean visible);

    /**
     * set window title
     *
     * @param title title of window
     */
    public void setTitle (String title);

    /**
     * set window size
     *
     * @param width width of window
     * @param height height of window
     */
    public void setSize (int width, int height);

    /**
    * set minimum size of window
     *
     * @param width minimum width of window
     * @param height minimum height of window
    */
    public void setMinSize (int width, int height);

    /**
     * set maximum size of window
     *
     * @param width maximum width of window
     * @param height maximum height of window
     */
    public void setMaxSize (int width, int height);

    /**
    * set window position
    */
    public void setPosition (int x, int y);

    /**
    * set resizeable of window, if true window is resizeable, else it isnt resizeable
    */
    public void setResizeable (boolean resizeable);

    /**
    * set window icon
    */
    public void setIcon (String imagePath) throws IOException;

    /**
    * set default window icon
    */
    public void setDefaultIcon ();

    /**
    * add an key callback which is called, if key was pressed or released
     *
     * @param callback key callback
    */
    public void addKeyCallback (KeyCallback callback);

    /**
     * remove key callback
     *
     * @param callback key callback
     */
    public void removeKeyCallback (KeyCallback callback);

    /**
    * prepare rendering of window
    */
    public void prepareRendering ();

    /**
    * check, if window should be closed
     *
     * @return true, if window should be closed
    */
    public boolean shouldClose ();

    /**
    * set to true, if window should exit, when exit button in window was pressed
     *
     * @param exitOnClose true, if window should exit, when exit button in window was pressed
    */
    public void setExitOnClose (boolean exitOnClose);

    /**
    * swap buffers
    */
    public void swap ();

    /**
    * close window
    */
    public void close ();

    /**
    * destroy window
    */
    public void destroy ();

}
