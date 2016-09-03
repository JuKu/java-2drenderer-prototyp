package com.jukusoft.renderer2d.prototyp.engine.entity;

import com.jukusoft.renderer2d.prototyp.engine.entity.system.ISystem;

/**
 * Created by Justin on 03.09.2016.
 */
public interface EntitySystemManager {

    /**
    * update entites, execute systems
    */
    public void update (EntityComponentSystem ecs, double delta);

    /**
    * render entites
    */
    public void render (EntityComponentSystem ecs);

    /**
    * process input
    */
    public void processInput (EntityComponentSystem ecs);

    /**
    * add entity system
     *
     * @param name unique name of entity system
     * @param system instance of entity system
    */
    public void addSystem (final String name, ISystem system);

    /**
    * remove entity system
     *
     * @param name unique name of entity system
    */
    public void removeSystem (final String name);

}
