package com.jukusoft.renderer2d.prototyp.engine.mesh;

/**
 * Created by Justin on 24.08.2016.
 */
public class GPUMesh<T extends Mesh> {

    /**
    * instance of mesh
    */
    protected final T mesh;

    public GPUMesh(T mesh) {
        this.mesh = mesh;
    }

    public long getID () {
        return this.mesh.getID();
    }

    public T getMesh () {
        return this.mesh;
    }

    public void cleanUp () {
        //
    }

    @Override
    public void finalize () {
        this.cleanUp();
    }

}
