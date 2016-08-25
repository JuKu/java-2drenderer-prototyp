package com.jukusoft.renderer2d.prototyp.gamestate;

import com.jukusoft.renderer2d.prototyp.engine.app.GameApp;
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

    @Override
    public <T extends GameState> void onInit(GameStateManager<T> gameStateManager, GameApp app) {
        Logger.getRootLogger().info("GameState4::onInit().");

        getWindow().setClearColor(0, 0, 0, 0);
    }

    @Override
    public void render (GameApp app) {
        getWindow().clear();

        if (getWindow().wasResized()) {
            getWindow().setViewPort(0, 0, getWindow().getWidth(), getWindow().getHeight());
            getWindow().setResizedFlag(false);
        }
    }

}
