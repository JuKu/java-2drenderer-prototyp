package com.jukusoft.renderer2d.prototyp.engine.mesh;

/**
 * Created by Justin on 24.08.2016.
 */
public class GPUMeshData<T extends Mesh> {

    /**
    * instance of mesh
    */
    protected final T mesh;

    public GPUMeshData (T mesh) {
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
