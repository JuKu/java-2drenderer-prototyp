package com.jukusoft.renderer2d.prototyp.engine.entity.system;

import com.jukusoft.renderer2d.prototyp.engine.entity.EntityComponentSystem;

/**
 * Created by Justin on 04.09.2016.
 */
public interface UpdateSystem extends ISystem {

    /**
    * update entities
    */
    public void update (EntityComponentSystem ecs, double delta);

}
