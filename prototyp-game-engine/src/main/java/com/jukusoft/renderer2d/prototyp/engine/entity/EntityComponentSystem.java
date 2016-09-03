package com.jukusoft.renderer2d.prototyp.engine.entity;

import com.jukusoft.renderer2d.prototyp.engine.entity.component.IComponent;

import java.util.List;

/**
 * Created by Justin on 03.09.2016.
 */
public interface EntityComponentSystem {

    /**
    * check if entity exists
     *
     * @param entityID unique id of entity
     *
     * @return true, if entity exists
    */
    public boolean existsEntity (long entityID);

    /**
    * create new entity with list of conmponents
     *
     * @param components list of components
     *
     * @return new generated id of entity
    */
    public long createEntity (IComponent... components);

    /**
    * remove entity
     *
     * @param entityID unique id of entity
    */
    public void removeEntity (long entityID);

    /**
    * add component to entity
     *
     * @param entityID unique id of entity
     * @param component instance of component
    */
    public void addComponent (long entityID, IComponent component);

    /**
    * remove component from entity
     *
     * @param entityID unique id of entity
     * @param cls class of component
    */
    public void removeComponent (long entityID, Class<IComponent> cls);

    /**
    * get instance of component of entity
     *
     * @param entityID unique id of entity
     * @param cls class of component
     *
     * @return instance of component of entity
    */
    public <T extends IComponent> T getComponent (long entityID, Class<T> cls);

    /**
    * find entities with component and return list of ids from entites
     *
     * @return list with entity ids which contains component
    */
    public List<Long> findEntitiesWithComponent (Class<? extends IComponent> cls);

    /**
    * generate new unique id of entity
    */
    public long generateNewEntityID ();

}
