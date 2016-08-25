package com.jukusoft.renderer2d.prototyp.engine.mesh;

/**
 * Created by Justin on 24.08.2016.
 */
public abstract class GPUMesh<T extends Mesh> implements AutoCloseable {

    /**
    * instance of mesh
    */
    protected final T mesh;

    /**
    * flag if mesh was cleaned up
    */
    protected volatile boolean isCleanedUp = false;

    public GPUMesh(T mesh) {
        this.mesh = mesh;
    }

    public long getID () {
        return this.mesh.getID();
    }

    public T getMesh () {
        return this.mesh;
    }

    public abstract void init ();

    public abstract void render ();

    public abstract void cleanUp ();

    @Override
    public void finalize () {
        if (!this.isCleanedUp) {
            this.cleanUp();

            this.isCleanedUp = true;
        }
    }

    @Override
    public void close () throws Exception {
        if (!this.isCleanedUp) {
            this.cleanUp();

            this.isCleanedUp = true;
        }
    }

}
