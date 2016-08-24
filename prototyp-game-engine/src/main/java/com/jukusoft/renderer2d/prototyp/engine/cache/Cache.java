package com.jukusoft.renderer2d.prototyp.engine.cache;

/**
 * Caching Api
 *
 * Created by Justin on 21.01.2016.
 */
public interface Cache<K, T> {

    /**
    * put object to cache
     *
     * @param key key
     * @param value cache value
    */
    public void put(K key, T value);

    /**
    * get caching value
     *
     * @param key key
     *
     * @return cache value
    */
    public T get(K key);

    /**
    * get size of cache items
     *
     * @return cache size
    */
    public int size();

    /**
    * check, if cache contains key
     *
     * @param key key
     *
     * @return true, if key exists in cache
    */
    public boolean containsKey(K key);

    /**
    * clear all cache entries
    */
    public void clear();

    /**
    * shutdown cache, for example interrupt thread
    */
    public void shutdown();

}
