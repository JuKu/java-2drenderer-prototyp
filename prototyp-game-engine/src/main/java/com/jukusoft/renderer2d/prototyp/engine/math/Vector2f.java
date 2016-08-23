package com.jukusoft.renderer2d.prototyp.engine.math;

/**
 * Created by Justin on 23.08.2016.
 */
public class Vector2f implements Cloneable {

    /**
     * vector coordinates
     */
    protected volatile float x = 0;
    protected volatile float y = 0;

    /**
     * default constructor
     *
     * @param x x coordinate of vector
     * @param y y coordinate of vector
     */
    public Vector2f (float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * default constructor
     */
    public Vector2f () {
        //
    }

    public float getX () {
        return x;
    }

    public float getY () {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public Vector2f clone () {
        return new Vector2f(this.x, this.y);
    }

}
