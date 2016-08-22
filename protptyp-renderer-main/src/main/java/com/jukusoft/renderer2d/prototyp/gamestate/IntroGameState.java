package com.jukusoft.renderer2d.prototyp.gamestate;

import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.impl.BasicGameState;
import org.apache.log4j.Logger;

/**
 * Created by Justin on 22.08.2016.
 */
public class IntroGameState extends BasicGameState {

    @Override
    public <T extends GameState> void onInit(GameStateManager<T> gameStateManager) {
        //TODO: initialize game state

        Logger.getRootLogger().info("IntroGameState::onInit().");
    }

    @Override
    public void onShutdown() {
        //TODO: shutdown game state and release memory

        Logger.getRootLogger().info("IntroGameState::onShutdown().");
    }

    @Override
    public void update (double delta) {
        //TODO: update game state
    }

    @Override
    public void render () {
        //TODO: render game state
    }

}
