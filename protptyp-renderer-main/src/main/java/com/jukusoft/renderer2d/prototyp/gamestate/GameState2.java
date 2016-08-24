package com.jukusoft.renderer2d.prototyp.gamestate;

import com.jukusoft.renderer2d.prototyp.engine.app.GameApp;
import com.jukusoft.renderer2d.prototyp.engine.exception.ShaderException;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.impl.BasicGameState;
import com.jukusoft.renderer2d.prototyp.engine.shader.OpenGLShaderProgram;
import com.jukusoft.renderer2d.prototyp.engine.utils.FileUtils;
import com.jukusoft.renderer2d.prototyp.engine.utils.OpenGLUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 24.08.2016.
 */
public class GameState2 extends BasicGameState {

    /**
    * instance of shader program
    */
    protected OpenGLShaderProgram shaderProgram = null;

    @Override
    public <T extends GameState> void onInit(GameStateManager<T> gameStateManager, GameApp app) {
        Logger.getRootLogger().info("GameState2::onInit().");

        //create new shader program
        this.shaderProgram = new OpenGLShaderProgram();

        try {
            //add vertex shader
            this.shaderProgram.setVertexShader(FileUtils.readFile("./data/shader/vertex.vs", StandardCharsets.UTF_8));

            //add fragment shader
            this.shaderProgram.setFragmentShader(FileUtils.readFile("./data/shader/fragment.fs", StandardCharsets.UTF_8));

            //link shader program
            this.shaderProgram.link();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
