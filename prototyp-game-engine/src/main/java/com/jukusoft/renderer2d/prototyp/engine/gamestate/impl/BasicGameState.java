package com.jukusoft.renderer2d.prototyp.engine.gamestate.impl;

import com.jukusoft.renderer2d.prototyp.engine.app.GameApp;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;

/**
 * Created by Justin on 17.08.2016.
 */
public class BasicGameState implements GameState {

    @Override
    public <T extends GameState> void onInit(GameStateManager<T> gameStateManager, GameApp app) {

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public <T extends GameState> void onStart(GameStateManager<T> gameStateManager, GameApp app) {

    }

    @Override
    public void onResized(int width, int height) {

    }

    @Override
    public void render(GameApp app) {

    }

    @Override
    public void processInputEvents() {

    }

    @Override
    public void update(GameApp app, double delta) {

    }

}
