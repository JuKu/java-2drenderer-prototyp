package com.jukusoft.renderer2d.prototyp.engine.window;

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
    * destroy window
    */
    public void destroy ();

}
