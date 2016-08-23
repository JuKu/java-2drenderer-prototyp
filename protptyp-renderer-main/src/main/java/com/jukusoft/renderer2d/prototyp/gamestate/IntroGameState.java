package com.jukusoft.renderer2d.prototyp.gamestate;

import com.jukusoft.renderer2d.prototyp.engine.app.GameApp;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.impl.BasicGameState;
import org.apache.log4j.Logger;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

/**
 * Created by Justin on 22.08.2016.
 */
public class IntroGameState extends BasicGameState {

    protected volatile float color = 0.0f;
    protected volatile int direction = 0;

    @Override
    public <T extends GameState> void onInit(GameStateManager<T> gameStateManager, GameApp app) {
        //TODO: initialize game state

        Logger.getRootLogger().info("IntroGameState::onInit().");
    }

    @Override
    public void onShutdown() {
        //TODO: shutdown game state and release memory

        Logger.getRootLogger().info("IntroGameState::onShutdown().");
    }

    @Override
    public void update (GameApp app, double delta) {
        //TODO: update game state

        if (getWindow().isKeyPressed(GLFW_KEY_UP) ) {
            direction = 1;
        } else if (getWindow().isKeyPressed(GLFW_KEY_DOWN) ) {
            direction = -1;
        } else {
            direction = 0;
        }

        color += this.direction * 0.01f;
        if (color > 1) {
            color = 1.0f;
        } else if ( color < 0 ) {
            color = 0.0f;
        }
    }

    @Override
    public void render (GameApp app) {
        Logger.getRootLogger().debug("color: " + color);

        //set clear color
        this.getWindow().setClearColor(color, color, color, 0.0f);

        getWindow().clear();

        //TODO: render game state
    }

    @Override
    public void onResized (int width, int height) {
        Logger.getRootLogger().info("window was resized, new width: " + width + ", new height: " + height);
    }

}
