package com.jukusoft.renderer2d.prototyp;

import com.jukusoft.renderer2d.prototyp.engine.app.SimpleGameStateApp;
import com.jukusoft.renderer2d.prototyp.engine.exception.GameStateNotFoundException;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;
import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import com.jukusoft.renderer2d.prototyp.gamestate.IntroGameState;

/**
 * Created by Justin on 22.08.2016.
 */
public class MySimpleGameStateApp extends SimpleGameStateApp<GameState> {

    public MySimpleGameStateApp(boolean useMultiThreading, int fixedFPS, int fixedUPS) {
        super(useMultiThreading, fixedFPS, fixedUPS);
    }

    @Override
    protected void onInitGame(GameStateManager<GameState> stateManager) {
        //create new game state
        IntroGameState introGameState = new IntroGameState();

        //add game state
        stateManager.addGameState("intro", introGameState);

        //push game state to activate game state
        try {
            stateManager.pushGameState("intro");
        } catch (GameStateNotFoundException e) {
            e.printStackTrace();

            //TODO: replace this line

            System.exit(1);
        }
    }

    @Override
    protected void onCreateWindow(IWindow window) {
        //
    }

}
