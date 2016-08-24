package com.jukusoft.renderer2d.prototyp.engine.cache;

import com.jukusoft.renderer2d.prototyp.engine.exception.CacheEntryNotFoundException;
import com.jukusoft.renderer2d.prototyp.engine.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 24.08.2016.
 */
public class FileCache {

    /**
    * instance of file cache - singleton design pattern
    */
    protected static FileCache instance = null;

    /**
    * global cache directory
    */
    protected File cacheDir = null;

    /**
    * default constructor
     *
     * @param path cache path
    */
    public FileCache (String path) {
        //set cache path
        this.cacheDir = new File(path);

        //check, if cache directory doesnt exists
        if (!this.cacheDir.exists()) {
            //create new cache directory
            this.cacheDir.mkdirs();
        }
    }

    /**
    * put cache entry to cache
     *
     * @param area area of cache entry
     * @param key key of cache entry
     * @param content content of cache entry
     *
     * @throws IOException
    */
    public void put (String area, String key, String content) throws IOException {
        //create area directory, if directory doesnt exists
        this.createAreaIfAbsent(area);

        //TODO: use serialization if it isnt an string

        //write content to file
        FileUtils.writeFile(getCachePath(area, key), content, StandardCharsets.UTF_8);
    }

    /**
    * get cache entry
     *
     * @param area area of cache entry
     * @param key key of cache entry
     *
     * @throws CacheEntryNotFoundException
    */
    public String get (String area, String key) throws CacheEntryNotFoundException {
        //check, if cache entry doesnt exists
        if (!this.contains(area, key)) {
            //throw cache entry not found exception
            throw new CacheEntryNotFoundException("Cache entry with area: " + area + " and key: " + key + ", path: " + this.getCachePath(area, key) + " doesnt exists.");
        }

        try {
            //read file and return content
            return FileUtils.readFile(this.getCachePath(area, key), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();

            throw new CacheEntryNotFoundException("Cache entry with area: " + area + " and key: " + key + ", path: " + this.getCachePath(area, key) + " couldnt be readed, IOException thrown.");
        }
    }

    /**
    * check, if cache entry exists
     *
     * @param area area of cache entry
     * @param key key of cache entry
    */
    public boolean contains (String area, String key) {
        //check, if cache file exists and return true, if file exists or false, if file doesnt exists
        return new File(this.getCachePath(area, key)).exists();
    }

    /**
    * create area directory if directory doesnt exists
     *
     * @param area area name
    */
    private void createAreaIfAbsent (String area) {
        //create instance of file
        File f = new File(this.cacheDir.getAbsolutePath() + "/" + area);

        //check, if directory exists
        if (!f.exists()) {
            //create directory
            f.mkdirs();
        }
    }

    /**
    * get cache path from cache file
     *
     * @param area cache area
     * @param key cache key
     *
     * @return path to cache file
    */
    private String getCachePath (String area, String key) {
        return this.cacheDir.getAbsolutePath() + "/" + area + "/" + key + ".tmp";
    }

    /**
    * get instance of file cache
     *
     * @return instance of file cache
    */
    public static FileCache getInstance () {
        //check, if instance doesnt exists
        if (FileCache.instance == null) {
            //create new instance of file cache
            FileCache.instance = new FileCache("./cache");
        }

        //return instance of file cache
        return FileCache.instance;
    }

    /**
    * set instance of file cache
     *
     * @param instance new instance of file cache
    */
    public static void setInstance (FileCache instance) {
        //replace instance
        FileCache.instance = instance;
    }

}
