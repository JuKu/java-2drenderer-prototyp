package com.jukusoft.renderer2d.prototyp.engine.entity.component;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

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

    /**
    * float buffer for relative x and y position of image
    */
    protected FloatBuffer relPosBuffer = BufferUtils.createFloatBuffer(2);

    public Image (String imagePath, float relX, float relY) {
        this.imagePath = imagePath;

        //set relative position
        this.relPosBuffer.put(0, relX);
        this.relPosBuffer.put(1, relY);

        this.relPosBuffer.flip();
    }

    public Image (String imagePath) {
        this.imagePath = imagePath;

        //set relative position to 0, 0
        this.relPosBuffer.put(0, 0);
        this.relPosBuffer.put(1, 0);

        this.relPosBuffer.flip();
    }

    public String getImagePath () {
        return this.imagePath;
    }

    public float getRelX () {
        return this.relPosBuffer.get(0);
    }

    public float getRelY () {
        return this.relPosBuffer.get(1);
    }

}
