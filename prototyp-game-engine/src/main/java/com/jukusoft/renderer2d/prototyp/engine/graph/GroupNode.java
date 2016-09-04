package com.jukusoft.renderer2d.prototyp.engine.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Justin on 04.09.2016.
 */
public class GroupNode<T extends Node> extends Node {

    /**
    * list with child nodes
    */
    protected final List<T> childNodes = new ArrayList<>();

    /**
    * add child node
     *
     * @param node child node
    */
    public void addChild (T node) {
        //add node to list
        this.childNodes.add(node);

        //sort list
        Collections.sort(this.childNodes);
    }

    /**
    * remove child node
     *
     * @param node instance of child node
    */
    public void removeChild (T node) {
        this.childNodes.remove(node);
    }

    /**
    * list child nodes
     *
     * @return list of child nodes
    */
    public List<T> listChildNodes () {
        return Collections.unmodifiableList(this.childNodes);
    }

    /**
    * process graph recursive
    */
    @Override
    public void processRecursive (Consumer<Node> consumer, boolean processGroupNodes) {
        //process this node first
        if (processGroupNodes) {
            consumer.accept(this);
        }

        //iterate through child nodes
        for (Node node : this.childNodes) {
            node.processRecursive(consumer, processGroupNodes);
        }
    }

}
