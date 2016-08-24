package com.jukusoft.renderer2d.prototyp.engine.asset;

import java.util.ArrayList;
import java.util.List;

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
     * list with asset manager initializer to add asset loader
     */
    protected static List<AssetManagerInitializer> assetManagerInitializerList = new ArrayList<>();

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

            //initialize asset manager and add asset loaders
            callInitializers();
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

    /**
    * add initializer for asset manager to add Assetloader
     *
     * @param initializer instance of asset manager initializer
    */
    public static void addInitializer (AssetManagerInitializer initializer) {
        //check, if instance of asset manager already exists
        if (instance != null) {
            throw new IllegalStateException("AssetManager is already initialized, you have to add initializers before any call of getInstance().");
        }

        //add initializer to list
        assetManagerInitializerList.add(initializer);
    }

    /**
    * call asset manager initializers and add asset loaders
    */
    private static void callInitializers () {
        //initialize asset manager
        for (AssetManagerInitializer initializer : assetManagerInitializerList) {
            //add asset loaders
            initializer.initializeAssetLoader(instance);
        }
    }

}
