package com.jukusoft.renderer2d.prototyp.engine.entity.component;

/**
 * entity component to draw an image on position of Position2D
 *
 * @see Position2D
 *
 * Created by Justin on 04.09.2016.
 */
public class Image implements IComponent {

    /**
    * path to image
    */
    protected String imagePath = "";

    public Image (String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath () {
        return this.imagePath;
    }

}
