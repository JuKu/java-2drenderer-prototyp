package com.jukusoft.renderer2d.prototyp.engine.entity.listener;

/**
 * Created by Justin on 03.09.2016.
 */
public interface EntityListener {

    /**
    * entity was added
     *
     * @param entityID unique id of entity
    */
    public void entityAdded (long entityID);

    /**
    * entity was removed
     *
     * @param entityID unique id of entity
    */
    public void entityRemoved (long entityID);

}
