package com.jukusoft.renderer2d.prototyp.engine.opengl.buffer;

import com.jukusoft.renderer2d.prototyp.engine.asset.Asset;

import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by Justin on 24.08.2016.
 */
public class VertexArrayObject extends Asset {

    /**
    * if of vertex array object on gpu
    */
    protected int vaoID = 0;

    /**
    * default constructor
    */
    public VertexArrayObject () {
        //create VAO on gpu
        this.create();
    }

    protected void create () {
        //create new vertex array object on gpu
        this.vaoID = glGenVertexArrays();
    }

    public void bind () {
        //bind VAO
        glBindVertexArray(this.vaoID);
    }

    public void unbind () {
        //unbind the VAO
        glBindVertexArray(0);
    }

    public void disable () {
        glDisableVertexAttribArray(0);
    }

    public void enable () {
        glEnableVertexAttribArray(0);
    }

    public void delete () {
        glBindVertexArray(0);
        glDeleteVertexArrays(this.vaoID);
    }

    @Override
    public void cleanUp () {
        this.delete();
    }

}
