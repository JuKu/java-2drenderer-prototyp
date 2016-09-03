package com.jukusoft.renderer2d.prototyp.engine.entity;

import com.jukusoft.renderer2d.prototyp.engine.entity.system.ISystem;
import com.jukusoft.renderer2d.prototyp.engine.entity.system.InputSystem;
import com.jukusoft.renderer2d.prototyp.engine.entity.system.RenderSystem;
import com.jukusoft.renderer2d.prototyp.engine.entity.system.UpdateSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Justin on 03.09.2016.
 */
public class DefaultEntitySystemManager implements EntitySystemManager {

    /**
    * map with entity systems
    */
    protected Map<String,ISystem> systemMap = new ConcurrentHashMap<>();

    /**
    * cache with input systems
    */
    protected final List<InputSystem> inputSystemCache = new ArrayList<>();

    /**
     * cache with update systems
     */
    protected final List<UpdateSystem> updateSystemCache = new ArrayList<>();

    /**
     * cache with render systems
     */
    protected final List<RenderSystem> renderSystemCache = new ArrayList<>();

    @Override
    public void update(EntityComponentSystem ecs, double delta) {
        //iterate through update systems
        for (UpdateSystem updateSystem : this.updateSystemCache) {
            //update system
            updateSystem.update(ecs, delta);
        }
    }

    @Override
    public void render(EntityComponentSystem ecs) {
        //iterate through update systems
        for (RenderSystem renderSystem : this.renderSystemCache) {
            //render entities
            renderSystem.render(ecs);
        }
    }

    @Override
    public void processInput(EntityComponentSystem ecs) {
        //iterate through input systems
        for (InputSystem inputSystem : this.inputSystemCache) {
            //process input
            inputSystem.processInput(ecs);
        }
    }

    @Override
    public void addSystem(String name, ISystem system) {
        if (system instanceof InputSystem) {
            synchronized (this.inputSystemCache) {
                //add to cache
                this.inputSystemCache.add((InputSystem) system);
            }
        }

        if (system instanceof UpdateSystem) {
            synchronized (this.updateSystemCache) {
                //add to cache
                this.updateSystemCache.add((UpdateSystem) system);
            }
        }

        if (system instanceof RenderSystem) {
            synchronized (this.renderSystemCache) {
                //add to cache
                this.renderSystemCache.add((RenderSystem) system);
            }
        }

        this.systemMap.put(name, system);
    }

    @Override
    public void removeSystem(String name) {
        //get instance of system
        ISystem system = this.systemMap.get(name);

        //check, if system exists
        if (system != null) {
            if (system instanceof InputSystem) {
                synchronized (this.inputSystemCache) {
                    //remove from cache
                    this.inputSystemCache.remove((InputSystem) system);
                }
            }

            if (system instanceof UpdateSystem) {
                synchronized (this.updateSystemCache) {
                    //remove from cache
                    this.updateSystemCache.remove((UpdateSystem) system);
                }
            }

            if (system instanceof RenderSystem) {
                synchronized (this.renderSystemCache) {
                    //remove from cache
                    this.renderSystemCache.remove((RenderSystem) system);
                }
            }
        }

        //remove system from map
        this.systemMap.remove(name);
    }

}
