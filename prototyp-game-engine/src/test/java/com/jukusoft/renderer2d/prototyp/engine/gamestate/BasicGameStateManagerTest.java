package com.jukusoft.renderer2d.prototyp.engine.gamestate;

import com.jukusoft.renderer2d.prototyp.engine.exception.GameStateNotFoundException;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.impl.BasicGameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.impl.BasicGameStateManager;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Justin on 17.08.2016.
 */
public class BasicGameStateManagerTest {

    @Test
    public void testAddGameState () {
        //create new game state manager
        GameStateManager<GameState> stateManager = new BasicGameStateManager<>();

        assertEquals("number of game states in BasicGameStateManager isnt 0.", 0, stateManager.countRegisteredGameStates());
        assertEquals("number of active game states in BasicGameStateManager isnt 0.", 0, stateManager.countActiveGameStates());

        //create new game state
        GameState state = new BasicGameState();

        //add game state
        stateManager.addGameState("gameState1", state);

        assertEquals("number of game states in BasicGameStateManager isnt 1.", 1, stateManager.countRegisteredGameStates());
        assertEquals("number of active game states in BasicGameStateManager isnt 0.", 0, stateManager.countActiveGameStates());
    }

    @Test
    public void testRemoveGameState () {
        //create new game state manager
        GameStateManager<GameState> stateManager = new BasicGameStateManager<>();

        //create new game state
        GameState state = new BasicGameState();

        //add game state
        stateManager.addGameState("gameState1", state);

        //remove game state
        stateManager.removeGameState("gameState1");

        assertEquals("number of game states in BasicGameStateManager isnt 0.", 0, stateManager.countRegisteredGameStates());
        assertEquals("number of active game states in BasicGameStateManager isnt 0.", 0, stateManager.countActiveGameStates());
    }

    @Test
    public void testRemoveAllGameStates () {
        //create new game state manager
        GameStateManager<GameState> stateManager = new BasicGameStateManager<>();

        //create new game state
        GameState state = new BasicGameState();

        //add 3 game states
        stateManager.addGameState("gameState1", state);
        stateManager.addGameState("gameState2", state);
        stateManager.addGameState("gameState3", state);

        assertEquals("number of game states in BasicGameStateManager isnt 3.", 3, stateManager.countRegisteredGameStates());
        assertEquals("number of active game states in BasicGameStateManager isnt 0.", 0, stateManager.countActiveGameStates());

        //remove all game states
        stateManager.removeAllGameStates();

        assertEquals("number of game states in BasicGameStateManager isnt 0.", 0, stateManager.countRegisteredGameStates());
        assertEquals("number of active game states in BasicGameStateManager isnt 0.", 0, stateManager.countActiveGameStates());
    }

    @Test (expected = GameStateNotFoundException.class)
    public void testPushGameStateNotFound () throws GameStateNotFoundException {
        //create new game state manager
        GameStateManager<GameState> stateManager = new BasicGameStateManager<>();

        //push game states
        stateManager.pushGameState("gameState1");
    }

    @Test
    public void testPushAndPopGameStates () throws GameStateNotFoundException {
        //create new game state manager
        GameStateManager<GameState> stateManager = new BasicGameStateManager<>();

        //create new game states
        GameState state = new BasicGameState();
        GameState state1 = new BasicGameState();
        GameState state2 = new BasicGameState();

        //add 3 game states
        stateManager.addGameState("gameState1", state);
        stateManager.addGameState("gameState2", state1);
        stateManager.addGameState("gameState3", state2);

        //push 3 game states
        stateManager.pushGameState("gameState1");
        stateManager.pushGameState("gameState3");
        stateManager.pushGameState("gameState2");

        assertEquals("number of game states in BasicGameStateManager isnt 3.", 3, stateManager.countRegisteredGameStates());
        assertEquals("number of active game states in BasicGameStateManager isnt 3.", 3, stateManager.countActiveGameStates());

        //get active game state on top
        GameState topState = null;
        Iterator<GameState> iterator = stateManager.activeGameStatesIterator();

        while (iterator.hasNext()) {
            topState = iterator.next();

            //because we only want to active state on top, we can break
            break;
        }

        assertNotNull("iterator is null", iterator);
        assertNotNull("active game state is null", topState);
        assertEquals("game state isnt equal.", topState, state);

        //pop state
        GameState newState = stateManager.pop();

        assertEquals("game state isnt equal to poped game state", newState, state1);
        assertEquals("game state isnt equal to poped game state", stateManager.pop(), state2);
        assertEquals("game state isnt equal to poped game state", stateManager.pop(), state);
    }

}
