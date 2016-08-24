package com.jukusoft.renderer2d.prototyp.engine.cache;

/**
 * Created by Justin on 21.01.2016.
 */
public class CacheEntry<T> implements Comparable<CacheEntry<T>> {

    /**
    * last access timestamp in ms
    */
    private volatile Long lastAccess = 0l;

    /**
    * value of cache entry
    */
    private T value = null;

    /**
    * default constructor
     *
     * @param value cache entry value
    */
    public CacheEntry (T value) {
        this.value = value;

        //set last access timestamp
        this.access();
    }

    /**
    * get cache value
     *
     * @return cache entry value
    */
    public T getValue () {
        //set last access timestamp
        this.access();

        //return value
        return this.value;
    }

    /**
    * put value
     *
     * @param value cache entry value
    */
    public void put (T value) {
        //set last access timestamp
        this.access();

        //set value
        this.value = value;
    }

    /**
    * set last access timestamp
    */
    protected void access () {
        //update lastAccess timestamp
        this.lastAccess = System.currentTimeMillis();
    }

    /**
    * get last access timestamp
     *
     * @return last access timestamp in ms
    */
    public Long getLastAccess () {
        return this.lastAccess;
    }

    /**
    * check, if cache entry is outdated
     *
     * @param TTL time to live in ms
     * @param now current timestamp in ms
     *
     * @return true, if cache entry is outdated
    */
    public boolean isOutdated (Long TTL, Long now) {
        return this.getLastAccess() + TTL < now;
    }

    @Override
    public int compareTo(CacheEntry<T> o) {
        return this.lastAccess.compareTo(o.getLastAccess());
    }
}
