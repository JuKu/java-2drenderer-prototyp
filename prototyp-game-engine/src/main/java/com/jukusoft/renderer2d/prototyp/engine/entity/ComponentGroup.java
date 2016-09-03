package com.jukusoft.renderer2d.prototyp.engine.entity;

import com.jukusoft.renderer2d.prototyp.engine.entity.component.IComponent;
import com.jukusoft.renderer2d.prototyp.engine.recycler.Recycleable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Justin on 03.09.2016.
 */
public class ComponentGroup implements Recycleable {

    /**
    * map with components
    */
    protected final Map<Class<? extends IComponent>,IComponent> componentMap;

    /**
    * default constructor
    */
    public ComponentGroup () {
        //create new concurrent hash map
        this.componentMap = new ConcurrentHashMap<>();
    }

    /**
    * add component to component group
     *
     * @param component instance of component
    */
    protected void add (IComponent component) {
        //add component to map
        this.componentMap.put(component.getClass(), component);
    }

    /**
     * add components to component group
     *
     * @param components instances of components
     */
    protected void add (IComponent... components) {
        //iterate through components
        for (IComponent component : components) {
            //add component
            this.add(component);
        }
    }

    /**
    * remove component from component group
     *
     * @param cls class of component
    */
    public void remove (Class<? extends IComponent> cls) {
        this.componentMap.remove(cls);
    }

    /**
    * check, if component exists in component group
     *
     * @param cls class of component
     *
     * @return true, if component group contains component
    */
    public boolean containsComponent (Class<IComponent> cls) {
        return this.componentMap.containsKey(cls);
    }

    /**
    * get instance of component
     *
     * @param cls class of component
     *
     * @return instance of component
    */
    public <T extends IComponent> T getComponent (Class<T> cls) {
        //get component from map
        IComponent component = this.componentMap.get(cls);

        //check, if component exists
        if (component != null) {
            //cast and return component
            return cls.cast(component);
        }

        //if component is null, return null
        return null;
    }

    /**
    * list all component types
     *
     * @return list with component classes
    */
    public List<Class<? extends IComponent>> listComponentTypes () {
        List<Class<? extends IComponent>> list = new ArrayList<>();

        //iterate through map
        for (Map.Entry<Class<? extends IComponent>,IComponent> entry : this.componentMap.entrySet()) {
            //add component class to list
            list.add(entry.getKey());
        }

        //return list of component classes
        return list;
    }

    @Override
    public void cleanUp () {
        //TODO: iterate through components and recycle them

        //clear components map
        this.componentMap.clear();
    }

}
