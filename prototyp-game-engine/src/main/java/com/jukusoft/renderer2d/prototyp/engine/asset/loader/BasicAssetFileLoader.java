package com.jukusoft.renderer2d.prototyp.engine.asset.loader;

import com.jukusoft.renderer2d.prototyp.engine.exception.AssetNotFoundException;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 27.02.2016.
 */
public abstract class BasicAssetFileLoader<K, T> implements AssetFileLoader<K, T> {

    /**
    * list with asset sources
    */
    private List<File> assetSourceList = new ArrayList<>();

    @Override
    public void addSearchPath(File assetDir) {
        this.assetSourceList.add(assetDir);
    }

    @Override
    public void removeSearchPath(File assetDir) {
        this.assetSourceList.remove(assetDir);
    }

    /**
    * get list of all asset sources
     *
     * @return list of asset sources as file list
    */
    protected List<File> listAssetSources () {
        return this.assetSourceList;
    }

    /**
    * search asset in asset sources and get path to asset file
     *
     * @param path relative path of asset file, for example font/Almendra.otf
     *
     * @throws AssetNotFoundException
     *
     * @return path to asset
    */
    protected String getAssetPath (String path) throws AssetNotFoundException {
        //check, if file extension is set
        if (!hasFileExtension(path)) {
            //add file extension
            path += getFileExtension();
        }

        for (File assetDir : this.assetSourceList) {
            String newPath = assetDir.getAbsolutePath() + File.separator + path;

            File f = new File(newPath);

            Logger.getRootLogger().debug("search for asset " + path + " in directory " + assetDir.getAbsolutePath() + ".");

            //check, if file exists
            if (f.exists()) {
                //return path to file
                return f.getAbsolutePath();
            }
        }

        throw new AssetNotFoundException("Couldnt find asset " + path + ".");
    }

    /**
    * get file extension of asset file, for example .otf
     *
     * @return file extension of asset file
    */
    protected abstract String getFileExtension ();

    /**
    * return true, if path ends with file extension
     *
     * @return true, if path ends with file extension
    */
    protected boolean hasFileExtension (String path) {
        return path.endsWith(getFileExtension());
    }

}
