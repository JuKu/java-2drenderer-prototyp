package com.jukusoft.renderer2d.prototyp.engine.mesh;

import com.jukusoft.renderer2d.prototyp.engine.exception.MeshNotFoundException;
import com.jukusoft.renderer2d.prototyp.engine.mesh.GPUMeshData;
import com.jukusoft.renderer2d.prototyp.engine.utils.MeshUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Justin on 24.08.2016.
 */
public class MeshRegistry {

    /**
    * map with GPUMeshData instances
    */
    protected Map<Long,GPUMeshData> GPUMeshDataMap = new ConcurrentHashMap<>();

    /**
    * instance of GPUMeshData registry
    */
    protected static MeshRegistry instance = null;

    /**
    * default constructor
    */
    protected MeshRegistry () {
        //
    }

    /**
    * put GPUMeshData to registry
     *
     * @return local unique id of GPUMeshData
    */
    public long put (GPUMeshData GPUMeshData) {
        //generate new local unique id of GPUMeshData
        long GPUMeshDataID = MeshUtils.generateID();

        //put GPUMeshData to map
        this.GPUMeshDataMap.put(GPUMeshDataID, GPUMeshData);

        return GPUMeshDataID;
    }

    /**
    * get GPUMeshData from registry
     *
     * @param GPUMeshDataID local unique id of GPUMeshData
     * @param GPUMeshDataClass requested class of GPUMeshData
     *
     * @return instance of GPUMeshData
    */
    public <T> T get (long GPUMeshDataID, Class<T> GPUMeshDataClass) throws MeshNotFoundException {
        //get GPUMeshData from map
        GPUMeshData GPUMeshData = this.GPUMeshDataMap.get(GPUMeshDataID);

        //check, if GPUMeshData exists
        if (GPUMeshData == null) {
            throw new MeshNotFoundException("Couldnt find GPUMeshData with id " + GPUMeshDataID + ", requested class: " + GPUMeshDataClass.getCanonicalName() + ".");
        }

        //cast GPUMeshData to specific class and return instance
        return (T) GPUMeshDataClass.cast(GPUMeshData);
    }

    /**
    * remove GPUMeshData from registry
     *
     * @param GPUMeshDataID local unique id of GPUMeshData
    */
    public void remove (long GPUMeshDataID) {
        //get GPUMeshData from map
        GPUMeshData GPUMeshData = this.GPUMeshDataMap.get(GPUMeshDataID);

        //remove GPUMeshData from map
        this.GPUMeshDataMap.remove(GPUMeshDataID);

        //check, if GPUMeshData exists
        if (GPUMeshData != null) {
            //cleanUp GPUMeshData
            GPUMeshData.cleanUp();
        }
    }

    /**
    * check, if GPUMeshData exists in registry
     *
     * @param GPUMeshDataID local unique id of GPUMeshData
     *
     * @return true, if GPUMeshData exists
    */
    public boolean contains (long GPUMeshDataID) {
        return this.GPUMeshDataMap.get(GPUMeshDataID) != null;
    }

    /**
    * get instance of GPUMeshData registry
     *
     * uses singleton design pattern
     *
     * @return instance of GPUMeshData registry
    */
    public static MeshRegistry getInstance () {
        //check, if instance is null
        if (MeshRegistry.instance == null) {
            //create new instance of GPUMeshRegistry
            MeshRegistry.instance = new MeshRegistry();
        }

        //return instance of GPUMeshData registry
        return MeshRegistry.instance;
    }

    /**
    * set instance of GPUMeshData registry
     *
     * @param instance instance of GPUMeshData registry
    */
    public static void setInstance (MeshRegistry instance) {
        //override instance
        MeshRegistry.instance = instance;
    }

}
