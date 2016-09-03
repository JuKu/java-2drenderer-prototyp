package com.jukusoft.renderer2d.prototyp.engine.entity;

import com.jukusoft.renderer2d.prototyp.engine.entity.component.IComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Justin on 03.09.2016.
 */
public class DefaultEntityComponentSystem implements EntityComponentSystem {

    /**
    * last entity ID
    */
    protected AtomicLong lastID = new AtomicLong(0);

    /**
    * map with entities
    */
    protected final Map<Long,ComponentGroup> entityMap;

    /**
    * component map cache
    */
    protected final Map<Class<? extends IComponent>,List<Long>> componentCache;

    public DefaultEntityComponentSystem () {
        //create new concurrent hash map
        this.entityMap = new ConcurrentHashMap<>();

        //create new concurrent hash map for component cache to find components faster
        this.componentCache = new ConcurrentHashMap<>();
    }

    @Override
    public boolean existsEntity(long entityID) {
        return this.entityMap.get(entityID) != null;
    }

    @Override
    public long createEntity(IComponent... components) {
        //generate new unique id of entity
        long entityID = this.generateNewEntityID();

        //TODO: use memory recycler instead

        //create new component group first
        ComponentGroup group = new ComponentGroup();

        //add components to component group
        group.add(components);

        //add component group to map
        this.entityMap.put(entityID, group);

        //iterate through components
        for (IComponent component : components) {
            //add entity to cache
            this.addComponentToCache(entityID, component);
        }

        //return unique id of entity
        return entityID;
    }

    @Override
    public void removeEntity(long entityID) {
        //TODO: recycle memory instead

        //get group with components
        ComponentGroup group = this.entityMap.get(entityID);

        //iterate through component types
        for (Class<? extends IComponent> cls : group.listComponentTypes()) {
            //remove component type from cache
            this.removeComponentFromCache(entityID, cls);
        }

        //remove entity component group from map
        this.entityMap.remove(entityID);
    }

    @Override
    public void addComponent(long entityID, IComponent component) {
        //get component group from map
        ComponentGroup group = this.entityMap.get(entityID);

        //check, if entity doesnt exists
        if (group == null) {
            //TODO: create entity instead

            throw new IllegalArgumentException("entity with entityID " + entityID + " doesnt exists, cannot add component of class " + component.getClass() + ".");
        }

        //add entity to cache
        this.addComponentToCache(entityID, component);

        //add component to component group
        group.add(component);
    }

    /**
    * add component to cache
    */
    protected void addComponentToCache (long entityID, IComponent component) {
        //get list with entities from cache
        List<Long> entities = this.componentCache.get(entityID);

        //check, if entities list doesnt exists
        if (entities == null) {
            //create new array list
            entities = new ArrayList<>();

            //add list to cache
            this.componentCache.put(component.getClass(),entities);
        }

        synchronized (entities) {
            //add entity to list
            entities.add(entityID);
        }
    }

    protected void removeComponentFromCache (long entityID, Class<? extends IComponent> cls) {
        //get list with components from cache
        List<Long> entities = this.componentCache.get(entityID);

        //check, if list exists
        if (entities != null) {
            //remove entity from list
            entities.remove(entityID);
        }
    }

    @Override
    public void removeComponent(long entityID, Class<IComponent> cls) {
        //remove component from cache
        this.removeComponentFromCache(entityID, cls);

        //get component group
        ComponentGroup group = this.entityMap.get(entityID);

        //check, if entity exists
        if (group != null) {
            //remove component from group
            group.remove(cls);
        }
    }

    @Override
    public <T extends IComponent> T getComponent(long entityID, Class<T> cls) {
        //get component group
        ComponentGroup group = this.entityMap.get(entityID);

        //check, if entity exists
        if (group != null) {
            //get component from component group
            T component = group.getComponent(cls);

            //return instance of component
            return component;
        }

        //return null, if entity doesnt exists
        return null;
    }

    @Override
    public List<Long> findEntitiesWithComponent(Class<? extends IComponent> cls) {
        //get list with entities which contains this component
        List<Long> list = this.componentCache.get(cls);

        //return readonly list
        return Collections.unmodifiableList(list);
    }

    @Override
    public long generateNewEntityID() {
        return this.lastID.incrementAndGet();
    }

}
