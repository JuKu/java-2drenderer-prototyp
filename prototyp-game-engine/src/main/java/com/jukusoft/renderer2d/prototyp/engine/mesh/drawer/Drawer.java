package com.jukusoft.renderer2d.prototyp.engine.mesh.drawer;

import com.jukusoft.renderer2d.prototyp.engine.mesh.Mesh;

/**
 * Created by Justin on 24.08.2016.
 */
public interface Drawer<T extends Mesh> {

    /**
    * draw mesh
    */
    public void draw (T mesh);

}
