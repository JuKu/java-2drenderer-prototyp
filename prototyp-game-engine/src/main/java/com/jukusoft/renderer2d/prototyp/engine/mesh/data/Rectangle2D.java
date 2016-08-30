package com.jukusoft.renderer2d.prototyp.engine.mesh.data;

import com.jukusoft.renderer2d.prototyp.engine.math.Vector3f;

/**
 * Created by Justin on 30.08.2016.
 */
public class Rectangle2D {

    /**
     * 4 vectors of rectangle
     */
    protected Vector3f v1 = new Vector3f();
    protected Vector3f v2 = new Vector3f();
    protected Vector3f v3 = new Vector3f();
    protected Vector3f v4 = new Vector3f();

    public Rectangle2D(float x, float y, float width, float height) {
        float z = 1;

        //calculate and set vectors
        this.v1.set(x, y, z);
        this.v2.set(x + width, y, z);
        this.v3.set(x + width, y + height, z);
        this.v4.set(x, y + height, z);
    }

}
