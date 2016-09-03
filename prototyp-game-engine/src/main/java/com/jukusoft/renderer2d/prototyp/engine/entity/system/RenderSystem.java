package com.jukusoft.renderer2d.prototyp.engine.entity.system;

import com.jukusoft.renderer2d.prototyp.engine.entity.EntityComponentSystem;

/**
 * Created by Justin on 04.09.2016.
 */
public interface RenderSystem extends ISystem {

    /**
    * render entities
    */
    public void render (EntityComponentSystem ecs);

}
