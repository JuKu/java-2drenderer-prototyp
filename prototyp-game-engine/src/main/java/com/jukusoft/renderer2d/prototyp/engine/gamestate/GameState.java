package com.jukusoft.renderer2d.prototyp.engine.gamestate;

/**
 * Created by Justin on 17.08.2016.
 */
public interface GameState {

    /**
    * create and initialize game state
     *
     * will be called, if game state will be created
    */
    public <T extends GameState> void onInit(GameStateManager<T> gameStateManager);

    /**
     * shutdown game state
     */
    public void onShutdown();

    /**
    * pause game state and switch to another game state
    */
    public void onPause();

    /**
    * switch to this game state and start
    */
    public <T extends GameState> void onStart(GameStateManager<T> gameStateManager);

    /**
    * render game state
    */
    public void render();

    /**
    * process input events
    */
    public void processInputEvents();

    /**
    * update game state
    */
    public void update(double delta);

}
