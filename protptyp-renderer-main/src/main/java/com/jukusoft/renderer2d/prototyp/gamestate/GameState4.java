package com.jukusoft.renderer2d.prototyp.gamestate;

import com.jukusoft.renderer2d.prototyp.engine.app.GameApp;
import com.jukusoft.renderer2d.prototyp.engine.entity.DefaultEntityComponentSystem;
import com.jukusoft.renderer2d.prototyp.engine.entity.DefaultEntitySystemManager;
import com.jukusoft.renderer2d.prototyp.engine.entity.EntityComponentSystem;
import com.jukusoft.renderer2d.prototyp.engine.entity.EntitySystemManager;
import com.jukusoft.renderer2d.prototyp.engine.entity.component.Image;
import com.jukusoft.renderer2d.prototyp.engine.entity.component.Position2D;
import com.jukusoft.renderer2d.prototyp.engine.entity.system.impl.Basic2DRenderSystem;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.impl.BasicGameState;
import com.jukusoft.renderer2d.prototyp.engine.math.Vector3f;
import com.jukusoft.renderer2d.prototyp.engine.mesh.data.GPUTriangle;
import com.jukusoft.renderer2d.prototyp.engine.mesh.data.Triangle;
import com.jukusoft.renderer2d.prototyp.engine.shader.OpenGLShaderProgram;
import com.jukusoft.renderer2d.prototyp.engine.utils.FileUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 25.08.2016.
 */
public class GameState4 extends BasicGameState {

    /**
    * instance of entity component system
    */
    protected EntityComponentSystem ecs = null;

    /**
    * instance of manager for entity systems
    */
    protected EntitySystemManager entitySystemManager = null;

    /**
    * unique id of player entity
    */
    protected long playerEntityID = 0l;

    public GameState4 () {
        //create new entity component system
        this.ecs = new DefaultEntityComponentSystem();

        //create new entity system manager
        this.entitySystemManager = new DefaultEntitySystemManager();

        //add basic 2D render system
        this.entitySystemManager.addSystem("BasicRenderSystem", new Basic2DRenderSystem());
    }

    @Override
    public <T extends GameState> void onInit(GameStateManager<T> gameStateManager, GameApp app) {
        Logger.getRootLogger().info("GameState4::onInit().");

        getWindow().setClearColor(0, 0, 0, 0);

        Logger.getRootLogger().info("add player entity.");

        //create new player entity
        this.playerEntityID = this.ecs.createEntity(
                new Position2D(0, 0),
                new Image("data/graphic/player/player.png")
        );
    }

    @Override
    public void render (GameApp app) {
        //clear window
        getWindow().clear();

        //check, if window was resized
        if (getWindow().wasResized()) {
            //reset viewport
            getWindow().setViewPort(0, 0, getWindow().getWidth(), getWindow().getHeight());

            //reset resized flag
            getWindow().setResizedFlag(false);
        }

        //this.entitySystemManager.render(this.ecs);
    }

}
