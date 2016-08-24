package com.jukusoft.renderer2d.prototyp.engine.asset.loader;

import com.jukusoft.renderer2d.prototyp.engine.exception.AssetNotFoundException;

/**
 * asset loader
 *
 * every type has its own asset loader, for example texture, font and so on.
 *
 * @link gamedev.stackexchange.com/questions/34051/how-should-i-structure-an-extensible-asset-loading-system
 * @link http://gamedev.stackexchange.com/questions/1702/how-to-design-an-assetmanager
 *
 * Created by Justin on 27.02.2016.
 */
public interface AssetLoader<K, V> {

    public V loadAsset(K key) throws AssetNotFoundException;

    public V loadAssetWithoutCaching(K key) throws AssetNotFoundException;

}
