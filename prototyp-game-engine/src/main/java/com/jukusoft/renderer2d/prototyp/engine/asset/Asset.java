package com.jukusoft.renderer2d.prototyp.engine.asset;

/**
 * an abstract class for assets to manage resources in resource manager / asset manager
 *
 * Created by Justin on 24.08.2016.
 */
public abstract class Asset {

    /**
    * reference counter
     *
     * on initial 1, because object was created
    */
    protected volatile int refCount = 1;

    /**
    * instance of resource manager / asset manager
    */
    protected ResourceManager resourceManager = null;

    /**
    * increment reference counter if asset is used by one more component / system
    */
    public void retain () {
        this.refCount++;

        //check, if asset was removed before and has to be reloaded
        if (this.refCount == 1) {
            //load asset
            this.onLoad();
        }
    }

    /**
    * decrement reference counter
    */
    public void release () {
        this.refCount--;

        //check, if asset can be removed
        if (this.refCount <= 0) {
            //notify listeners
            this.notifyListener();

            //cleanup asset
            this.cleanUp();
        }
    }

    /**
    * get reference counter
     *
     * @return counter of objects / components / systems, which are using this asset
    */
    public int refCount () {
        return this.refCount;
    }

    /**
    * notify listener to remove asset
    */
    private void notifyListener () {
        //check, if resource manager is set
        if (this.resourceManager != null) {
            //remove asset, because it isnt needed anymore
            this.resourceManager.cleanUpAsset(this);
        }
    }

    /**
    * cleanUp asset
    */
    protected abstract void cleanUp ();

    /**
    * will be called, if reference count is higher than 0, reload asset
    */
    protected void onLoad () {
        //
    }

}
