package com.jukusoft.renderer2d.prototyp.engine.mesh;

import com.jukusoft.renderer2d.prototyp.engine.exception.MeshNotFoundException;
import com.jukusoft.renderer2d.prototyp.engine.utils.MeshUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Justin on 24.08.2016.
 */
public class MeshRegistry {

    /**
    * map with mesh instances
    */
    protected Map<Long,Mesh> meshMap = new ConcurrentHashMap<>();

    /**
    * instance of mesh registry
    */
    protected static MeshRegistry instance = null;

    /**
    * default constructor
    */
    protected MeshRegistry () {
        //
    }

    /**
    * put mesh to registry
     *
     * @return local unique id of mesh
    */
    public long put (Mesh mesh) {
        //generate new local unique id of mesh
        long meshID = MeshUtils.generateID();

        //put mesh to map
        this.meshMap.put(meshID, mesh);

        return meshID;
    }

    /**
    * get mesh from registry
     *
     * @param meshID local unique id of mesh
     * @param meshClass requested class of mesh
     *
     * @return instance of mesh
    */
    public <T> T get (long meshID, Class<T> meshClass) throws MeshNotFoundException {
        //get mesh from map
        Mesh mesh = this.meshMap.get(meshID);

        //check, if mesh exists
        if (mesh == null) {
            throw new MeshNotFoundException("Couldnt find mesh with id " + meshID + ", requested class: " + meshClass.getCanonicalName() + ".");
        }

        //cast mesh to specific class and return instance
        return (T) meshClass.cast(mesh);
    }

    /**
    * remove mesh from registry
     *
     * @param meshID local unique id of mesh
    */
    public void remove (long meshID) {
        //get mesh from map
        Mesh mesh = this.meshMap.get(meshID);

        //remove mesh from map
        this.meshMap.remove(meshID);

        //check, if mesh exists
        if (mesh != null) {
            //cleanUp mesh
            mesh.cleanUp();
        }
    }

    /**
    * check, if mesh exists in registry
     *
     * @param meshID local unique id of mesh
     *
     * @return true, if mesh exists
    */
    public boolean contains (long meshID) {
        return this.meshMap.get(meshID) != null;
    }

    /**
    * get instance of mesh registry
     *
     * uses singleton design pattern
     *
     * @return instance of mesh registry
    */
    public static MeshRegistry getInstance () {
        //check, if instance is null
        if (MeshRegistry.instance == null) {
            //create new instance of MeshRegistry
            MeshRegistry.instance = new MeshRegistry();
        }

        //return instance of mesh registry
        return MeshRegistry.instance;
    }

    /**
    * set instance of mesh registry
     *
     * @param instance instance of mesh registry
    */
    public static void setInstance (MeshRegistry instance) {
        //override instance
        MeshRegistry.instance = instance;
    }

}
