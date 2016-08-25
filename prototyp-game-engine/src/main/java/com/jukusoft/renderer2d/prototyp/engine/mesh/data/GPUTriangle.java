package com.jukusoft.renderer2d.prototyp.engine.mesh.data;

import com.jukusoft.renderer2d.prototyp.engine.mesh.GPUMesh;
import com.jukusoft.renderer2d.prototyp.engine.opengl.buffer.FloatVertexBufferObject;
import com.jukusoft.renderer2d.prototyp.engine.opengl.buffer.VertexArrayObject;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;

/**
 * Created by Justin on 25.08.2016.
 */
public class GPUTriangle extends GPUMesh<Triangle> {

    /**
     * vertex buffer object
     */
    protected FloatVertexBufferObject vbo = null;

    /**
     * vertex array object
     */
    protected VertexArrayObject vao = new VertexArrayObject();

    public GPUTriangle (Triangle triangle) {
        super(triangle);
    }

    @Override
    public void init() {
        //bind VAO
        this.vao.bind();

        //create new vertex buffer object, bind to it, put data and unbind to it
        this.vbo = FloatVertexBufferObject.createAndPutBuffer(this.getMesh().getVerticesArray());

        //unbind VAO
        this.vao.unbind();
    }

    @Override
    public void onRender() {
        //bind VAO
        this.vao.bind();

        //enable VAO
        this.vao.enable();

        // Draw the vertices, mode - GL_TRINANGLES, first element - 0, count - 3
        glDrawArrays(GL_TRIANGLES, 0, 3);

        //disable VAO
        this.vao.disable();

        //unbind VAO
        this.vao.unbind();
    }

    @Override
    public void cleanUp() {

    }

}
