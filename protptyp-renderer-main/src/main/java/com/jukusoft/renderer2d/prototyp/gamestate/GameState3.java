package com.jukusoft.renderer2d.prototyp.gamestate;

import com.jukusoft.renderer2d.prototyp.engine.app.GameApp;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.impl.BasicGameState;
import com.jukusoft.renderer2d.prototyp.engine.opengl.buffer.FloatVertexBufferObject;
import com.jukusoft.renderer2d.prototyp.engine.shader.OpenGLShaderProgram;
import com.jukusoft.renderer2d.prototyp.engine.utils.FileUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;

/**
 * Created by Justin on 25.08.2016.
 */
public class GameState3 extends BasicGameState {

    @Override
    public <T extends GameState> void onInit(GameStateManager<T> gameStateManager, GameApp app) {
        Logger.getRootLogger().info("GameState3::onInit().");

        getWindow().setClearColor(0, 0, 0, 0);
    }

    @Override
    public void render (GameApp app) {
        getWindow().clear();

        if (getWindow().wasResized()) {
            getWindow().setViewPort(0, 0, getWindow().getWidth(), getWindow().getHeight());
            getWindow().setResizedFlag(false);
        }

        //TODO: render
    }

}
