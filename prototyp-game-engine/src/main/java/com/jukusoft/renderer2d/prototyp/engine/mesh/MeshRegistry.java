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
    * map with GPUMesh instances
    */
    protected Map<Long,GPUMesh> GPUMeshDataMap = new ConcurrentHashMap<>();

    /**
    * instance of GPUMesh registry
    */
    protected static MeshRegistry instance = null;

    /**
    * default constructor
    */
    protected MeshRegistry () {
        //
    }

    /**
    * put GPUMesh to registry
     *
     * @return local unique id of GPUMesh
    */
    public long put (GPUMesh GPUMesh) {
        //generate new local unique id of GPUMesh
        long GPUMeshDataID = MeshUtils.generateID();

        //put GPUMesh to map
        this.GPUMeshDataMap.put(GPUMeshDataID, GPUMesh);

        return GPUMeshDataID;
    }

    /**
    * get GPUMesh from registry
     *
     * @param GPUMeshDataID local unique id of GPUMesh
     * @param GPUMeshDataClass requested class of GPUMesh
     *
     * @return instance of GPUMesh
    */
    public <T> T get (long GPUMeshDataID, Class<T> GPUMeshDataClass) throws MeshNotFoundException {
        //get GPUMesh from map
        GPUMesh GPUMesh = this.GPUMeshDataMap.get(GPUMeshDataID);

        //check, if GPUMesh exists
        if (GPUMesh == null) {
            throw new MeshNotFoundException("Couldnt find GPUMesh with id " + GPUMeshDataID + ", requested class: " + GPUMeshDataClass.getCanonicalName() + ".");
        }

        //cast GPUMesh to specific class and return instance
        return (T) GPUMeshDataClass.cast(GPUMesh);
    }

    /**
    * remove GPUMesh from registry
     *
     * @param GPUMeshDataID local unique id of GPUMesh
    */
    public void remove (long GPUMeshDataID) {
        //get GPUMesh from map
        GPUMesh GPUMesh = this.GPUMeshDataMap.get(GPUMeshDataID);

        //remove GPUMesh from map
        this.GPUMeshDataMap.remove(GPUMeshDataID);

        //check, if GPUMesh exists
        if (GPUMesh != null) {
            //cleanUp GPUMesh
            GPUMesh.cleanUp();
        }
    }

    /**
    * check, if GPUMesh exists in registry
     *
     * @param GPUMeshDataID local unique id of GPUMesh
     *
     * @return true, if GPUMesh exists
    */
    public boolean contains (long GPUMeshDataID) {
        return this.GPUMeshDataMap.get(GPUMeshDataID) != null;
    }

    /**
    * get instance of GPUMesh registry
     *
     * uses singleton design pattern
     *
     * @return instance of GPUMesh registry
    */
    public static MeshRegistry getInstance () {
        //check, if instance is null
        if (MeshRegistry.instance == null) {
            //create new instance of GPUMeshRegistry
            MeshRegistry.instance = new MeshRegistry();
        }

        //return instance of GPUMesh registry
        return MeshRegistry.instance;
    }

    /**
    * set instance of GPUMesh registry
     *
     * @param instance instance of GPUMesh registry
    */
    public static void setInstance (MeshRegistry instance) {
        //override instance
        MeshRegistry.instance = instance;
    }

}
