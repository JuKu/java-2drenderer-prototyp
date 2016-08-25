package com.jukusoft.renderer2d.prototyp.gamestate;

import com.jukusoft.renderer2d.prototyp.engine.app.GameApp;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.impl.BasicGameState;
import com.jukusoft.renderer2d.prototyp.engine.math.Vector3f;
import com.jukusoft.renderer2d.prototyp.engine.mesh.data.GPUTriangle;
import com.jukusoft.renderer2d.prototyp.engine.mesh.data.Triangle;
import com.jukusoft.renderer2d.prototyp.engine.opengl.buffer.FloatVertexBufferObject;
import com.jukusoft.renderer2d.prototyp.engine.shader.OpenGLShaderProgram;
import com.jukusoft.renderer2d.prototyp.engine.utils.FileUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;

/**
 * Created by Justin on 25.08.2016.
 */
public class GameState3 extends BasicGameState {

    /**
    * triangle on gpu
    */
    protected GPUTriangle gpuTriangle = null;

    /**
     * instance of shader program
     */
    protected OpenGLShaderProgram shaderProgram = null;

    @Override
    public <T extends GameState> void onInit(GameStateManager<T> gameStateManager, GameApp app) {
        Logger.getRootLogger().info("GameState3::onInit().");

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

        //create new triangle
        Triangle triangle = new Triangle(new Vector3f(0.0f, 0.5f, 0.0f), new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.5f, -0.5f, 0.0f));
        this.gpuTriangle = new GPUTriangle(triangle);

        //initialize triangle on gpu
        this.gpuTriangle.init();

        getWindow().setClearColor(0, 0, 0, 0);
    }

    @Override
    public void render (GameApp app) {
        getWindow().clear();

        if (getWindow().wasResized()) {
            getWindow().setViewPort(0, 0, getWindow().getWidth(), getWindow().getHeight());
            getWindow().setResizedFlag(false);
        }

        //bind shader program
        this.shaderProgram.bind();

        //render triangle
        this.gpuTriangle.render();

        //unbind shader program
        this.shaderProgram.unbind();
    }

}
