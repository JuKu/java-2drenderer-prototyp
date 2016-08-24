package com.jukusoft.renderer2d.prototyp.engine.mesh;

import com.jukusoft.renderer2d.prototyp.engine.math.Vector3f;
import com.jukusoft.renderer2d.prototyp.engine.utils.MeshUtils;

/**
 * Created by Justin on 24.08.2016.
 */
public class Triangle extends Mesh {

    /**
    * 3 vectors of triangle
    */
    protected Vector3f v1 = new Vector3f();
    protected Vector3f v2 = new Vector3f();
    protected Vector3f v3 = new Vector3f();

    /**
    * local unique id of mesh
    */
    protected long meshID = 0;

    /**
    * default constructor
    */
    public Triangle (Vector3f v1, Vector3f v2, Vector3f v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;

        //generate new id of mesh
        this.meshID = MeshUtils.generateID();
    }

    /**
     * default constructor
     */
    public Triangle (float[] vertices) {
        if (vertices.length != 9) {
            throw new IllegalArgumentException("length of array has to be 9, because 3x 3 vectors are required.");
        }

        //generate new id of mesh
        this.meshID = MeshUtils.generateID();
    }

    /**
     * default constructor
     */
    public Triangle () {
        //generate new id of mesh
        this.meshID = MeshUtils.generateID();
    }

    public float[] getVerticesArray () {
        //create and return new array
        return new float[] {
                v1.getX(), v1.getY(), v1.getZ(),
                v2.getX(), v2.getY(), v2.getZ(),
                v3.getZ(), v3.getY(), v3.getZ()
        };
    }

}
