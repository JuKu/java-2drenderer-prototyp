package com.jukusoft.renderer2d.prototyp.engine.entity.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Justin on 03.09.2016.
 */
public class ComponentGroup {

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
    public void add (IComponent component) {
        //add component to map
        this.componentMap.put(component.getClass(), component);
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

}
