package com.jukusoft.renderer2d.prototyp.engine.mesh;

import com.jukusoft.renderer2d.prototyp.engine.utils.MeshUtils;

/**
 * Created by Justin on 24.08.2016.
 */
public abstract class Mesh {

    /**
     * local unique id of mesh
     */
    private long meshID = MeshUtils.generateID();

    public Mesh () {
        //
    }

    public long getID () {
        return this.meshID;
    }

    public void cleanUp () {
        //
    }

    @Override
    public void finalize () {
        this.cleanUp();
    }

}
