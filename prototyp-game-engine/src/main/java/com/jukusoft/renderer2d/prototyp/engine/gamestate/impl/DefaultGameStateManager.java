package com.jukusoft.renderer2d.prototyp.engine.gamestate.impl;

import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;

/**
 * Created by Justin on 17.08.2016.
 */
public class DefaultGameStateManager<T extends GameState> extends BasicGameStateManager<T> {

    public DefaultGameStateManager() {
        super();
    }

    public void render () {
        //iterate through active game states
        for (T state : this.iteratorQueue) {
            //render game state
            state.render();
        }
    }

    public void processInput () {
        //iterate through active game states
        for (T state : this.iteratorQueue) {
            //process input events on game state
            state.processInputEvents();
        }
    }

    public void update (double delta) {
        //iterate through active game states
        for (T state : this.iteratorQueue) {
            //update game state
            state.update(delta);
        }
    }

    public void onResized (int width, int height) {
        //iterate through active game states
        for (T state : this.iteratorQueue) {
            //update game state
            state.onResized(width, height);
        }
    }

}
