package com.jukusoft.renderer2d.prototyp.engine.app;

import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;

/**
 * Created by Justin on 22.08.2016.
 */
public interface GameStateEngine<T extends GameState> {

    /**
     * get instance of game state manager
     */
    public GameStateManager<T> getGameStateManager ();

}
