package com.jukusoft.renderer2d.prototyp.engine.scenegraph2d;

import com.jukusoft.renderer2d.prototyp.engine.graph.GroupNode;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Justin on 04.09.2016.
 */
public class SceneGraphNode extends GroupNode<SceneGraphNode> {

    protected volatile double rotation = 0;
    protected volatile double scale = 0;
    protected volatile float x = 0;
    protected volatile float y = 0;
    protected volatile double zLevel = 0;

    /**
    * flag, if node or child nodes was changed
    */
    protected AtomicBoolean wasChanged = new AtomicBoolean(false);

    public void setRotation (double alpha) {
        this.rotation = alpha;

        //set changed flag
        this.setChangedFlag();
    }

    public double getRotation () {
        return this.rotation;
    }

    public void setScale (double scale) {
        this.scale = scale;

        //set changed flag
        this.setChangedFlag();
    }

    public double getScale () {
        return this.scale;
    }

    public void setPosition (float x, float y) {
        this.x = x;
        this.y = y;

        //set changed flag
        this.setChangedFlag();
    }

    public float getX () {
        return this.x;
    }

    public float getY () {
        return this.y;
    }

    public void setChangedFlag () {
        this.wasChanged.set(true);
    }

    public void resetChangedFlag () {
        this.wasChanged.set(false);
    }

    public boolean wasChanged () {
        return this.wasChanged.get();
    }

    public void setZLevel (double zLevel) {
        this.zLevel = zLevel;

        //set changed flag
        this.setChangedFlag();
    }

    public double getZLevel () {
        return this.zLevel;
    }

    /**
     * add child node
     *
     * @param node child node
     */
    @Override
    public void addChild (SceneGraphNode node) {
        super.addChild(node);

        //set changed flag
        this.setChangedFlag();
    }

    /**
     * remove child node
     *
     * @param node instance of child node
     */
    @Override
    public void removeChild (SceneGraphNode node) {
        super.removeChild(node);

        //set changed flag
        this.setChangedFlag();
    }

}
