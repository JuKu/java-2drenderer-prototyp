package com.jukusoft.renderer2d.prototyp.engine.entity.component;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * basic position for entity in 2d map
 *
 * Created by Justin on 04.09.2016.
 */
public class Position2D implements IComponent {

    /**
    * float buffer for x and y coordinates
    */
    protected volatile FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(2);

    public Position2D (float x, float y) {
        this.floatBuffer.put(0, x);
        this.floatBuffer.put(1, y);
    }

    public void setX (float x) {
        this.floatBuffer.put(0, x);
    }

    public void setY (float y) {
        this.floatBuffer.put(1, y);
    }

    public float getX () {
        return this.floatBuffer.get(0);
    }

    public float getY () {
        return this.floatBuffer.get(1);
    }

}
