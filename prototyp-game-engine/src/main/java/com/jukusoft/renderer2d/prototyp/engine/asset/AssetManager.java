package com.jukusoft.renderer2d.prototyp.engine.asset;

/**
 * Asset manager to manages resources / assets
 *
 * Created by Justin on 24.08.2016.
 */
public class AssetManager {

    /**
    * instance of asset manager
    */
    protected static AssetManager instance = null;

    /**
    * default constructor
    */
    public AssetManager () {
        //
    }

    public static AssetManager getInstance () {
        //check, if instance of asset manager doesnt exists
        if (AssetManager.instance == null) {
            //create new asset manager
            AssetManager.instance = new AssetManager();
        }

        //return instance of asset manager
        return AssetManager.instance;
    }

    /**
    * set new instance of asset manager
     *
     * @param assetManager instance of new asset manager
    */
    public static void setInstance (AssetManager assetManager) {
        //override instance
        AssetManager.instance = assetManager;
    }

}
