package com.jukusoft.renderer2d.prototyp.engine.renderer;

/**
 * Created by Justin on 25.08.2016.
 */
public interface Renderer {

    /**
    * initialize renderer
    */
    public void init ();

    /**
    * clear screen
    */
    public void clear ();

    /**
    * render
    */
    public void render ();

}
