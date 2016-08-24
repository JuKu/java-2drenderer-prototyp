package com.jukusoft.renderer2d.prototyp.engine.asset;

/**
 * Initializer for asset manager
 *
 * Created by Justin on 27.02.2016.
 */
public interface AssetManagerInitializer {

    /**
    * initialize asset loader, add AssetLoader to AssetManager
     *
     * @param assetManager instance of asset manager
    */
    public void initializeAssetLoader(AssetManager assetManager);

}
