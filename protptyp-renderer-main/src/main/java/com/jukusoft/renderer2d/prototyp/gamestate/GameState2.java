package com.jukusoft.renderer2d.prototyp.gamestate;

import com.jukusoft.renderer2d.prototyp.engine.app.GameApp;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameState;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.GameStateManager;
import com.jukusoft.renderer2d.prototyp.engine.gamestate.impl.BasicGameState;
import com.jukusoft.renderer2d.prototyp.engine.opengl.buffer.FloatVertexBufferObject;
import com.jukusoft.renderer2d.prototyp.engine.opengl.buffer.VertexArrayObject;
import com.jukusoft.renderer2d.prototyp.engine.shader.OpenGLShaderProgram;
import com.jukusoft.renderer2d.prototyp.engine.utils.FileUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;

/**
 * Created by Justin on 24.08.2016.
 */
public class GameState2 extends BasicGameState {

    /**
    * instance of shader program
    */
    protected OpenGLShaderProgram shaderProgram = null;

    /**
    * array with vertices, this means 3x vectors which represents an triangle
    */
    protected float[] vertices = new float[]{
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };

    /**
    * vertex buffer object
    */
    protected FloatVertexBufferObject vbo = null;

    /**
    * vertex array object
    */
    protected VertexArrayObject vao = new VertexArrayObject();

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

        //bind VAO
        this.vao.bind();

        //create new vertex buffer object, bind to it, put data and unbind to it
        this.vbo = FloatVertexBufferObject.createAndPutBuffer(this.vertices);

        //unbind VAO
        this.vao.unbind();

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

        //bind VAO
        this.vao.bind();

        //enable VAO
        this.vao.enable();

        //disable VAO
        this.vao.disable();

        // Draw the vertices, mode - GL_TRINANGLES, first element - 0, count - 3
        glDrawArrays(GL_TRIANGLES, 0, 3);

        //unbind VAO
        this.vao.unbind();

        //unbind shader program
        this.shaderProgram.unbind();
    }

}
