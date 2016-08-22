package com.jukusoft.renderer2d.prototyp;

import com.jukusoft.renderer2d.prototyp.engine.app.SimpleGameApp;
import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;
import org.apache.log4j.Logger;

/**
 * Created by Justin on 21.08.2016.
 */
public class MySimpleGameApp extends SimpleGameApp {

    @Override
    protected void onCreateWindow(IWindow window) {
        //set window title
        window.setTitle("window title");

        //set window size
        window.setSize(600, 400);
    }

    @Override
    protected void initialize() {
        //TODO: initialize game
    }

    @Override
    public void update(double delta) {
        //TODO: update game state
    }

    @Override
    public void render() {
        //TODO: render window

        //https://github.com/lwjglgamedev/lwjglbook/blob/master/chapter02/src/main/java/org/lwjglb/engine/GameEngine.java

        //check, if window was resized
        if (this.wasResized()) {
            //window was resized

            Logger.getRootLogger().info("window was resized, new width: " + getWindow().getWidth() + ", new height: " + getWindow().getHeight());

            //reset resized flag
            this.setResizedFlag(false);
        }
    }

}
