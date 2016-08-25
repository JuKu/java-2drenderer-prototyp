package com.jukusoft.renderer2d.prototyp.engine.mesh.drawer;

import com.jukusoft.renderer2d.prototyp.engine.mesh.GPUMesh;

import java.util.Map;

/**
 * Created by Justin on 25.08.2016.
 */
public class DrawerRegistry {

    /**
    * map with drawers
    */
    protected Map<Class<GPUMesh>,Drawer> drawerMap = null;

    /**
    * instance of drawer registry
    */
    protected static DrawerRegistry instance = null;

    /**
    * default constructor
    */
    public DrawerRegistry () {
        //
    }

    /**
    * get instance of drawer registry
    */
    public static DrawerRegistry getInstance () {
        //check, if instance doesnt exists
        if (DrawerRegistry.instance == null) {
            //create new instance of drawer registry
            DrawerRegistry.instance = new DrawerRegistry();
        }

        //return instance of drawer registry
        return DrawerRegistry.instance;
    }

    /**
    * set instance of drawer registry
     *
     * @param instance instance of drawer registry
    */
    public static void setInstance (DrawerRegistry instance) {
        //override instance
        DrawerRegistry.instance = instance;
    }

}
