package com.jukusoft.renderer2d.prototyp.engine.math;

/**
 * An thread safe implementation of an vector with dimension 3
 *
 * Created by Justin on 23.08.2016.
 */
public class Vector3f implements Cloneable {

    /**
    * vector coordinates
    */
    protected volatile float x = 0;
    protected volatile float y = 0;
    protected volatile float z = 0;

    /**
     * default constructor
     *
     * @param x x coordinate of vector
     * @param y y coordinate of vector
     * @param z z coordinate of vector
     */
    public Vector3f (float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
    * default constructor
    */
    public Vector3f () {
        //
    }

    public float getX () {
        return x;
    }

    public float getY () {
        return y;
    }

    public float getZ () {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void set (float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Vector3f clone () {
        return new Vector3f(this.x, this.y, this.z);
    }

}
