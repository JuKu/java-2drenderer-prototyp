package com.jukusoft.renderer2d.prototyp.engine.entity.system;

import com.jukusoft.renderer2d.prototyp.engine.entity.EntityComponentSystem;

/**
 * Created by Justin on 04.09.2016.
 */
public interface InputSystem extends ISystem {

    /**
    * process input
    */
    public void processInput (EntityComponentSystem ecs);

}
