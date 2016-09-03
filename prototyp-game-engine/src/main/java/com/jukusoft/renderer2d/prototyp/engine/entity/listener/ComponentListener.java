package com.jukusoft.renderer2d.prototyp.engine.entity.listener;

import com.jukusoft.renderer2d.prototyp.engine.entity.component.IComponent;

/**
 * Created by Justin on 03.09.2016.
 */
public interface ComponentListener {

    /**
    * component was added
     *
     * @param entityID unique id of entity
     * @param component instance of component of entity
    */
    public void componentAdded (long entityID, IComponent component);

    /**
    * component was removed
     *
     * @param entityID unique id of entity
     * @param cls class of component
    */
    public void componentRemoved (long entityID, Class<IComponent> cls);

}
