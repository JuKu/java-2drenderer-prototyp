package com.jukusoft.renderer2d.prototyp;

import com.jukusoft.renderer2d.prototyp.engine.app.SimpleGameApp;
import com.jukusoft.renderer2d.prototyp.engine.window.IWindow;

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
    protected void onInit() {
        //TODO: initialize game
    }

    @Override
    public void update(double delta) {
        //TODO: update game state
    }

    @Override
    public void render() {
        //TODO: render window
    }

}
