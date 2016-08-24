package com.jukusoft.renderer2d.prototyp.engine.asset.loader;

import java.io.File;

/**
 * Created by Justin on 27.02.2016.
 */
public interface AssetFileLoader<K, T> extends AssetLoader<K, T> {

    /**
    * add path to directory, where asset loader can find assets
     *
     * @param assetDir asset directory
    */
    public void addSearchPath(File assetDir);

    /**
     * remove path to directory, where asset loader can find assets
     *
     * @param assetDir asset directory
     */
    public void removeSearchPath(File assetDir);

}
