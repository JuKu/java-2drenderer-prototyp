package com.jukusoft.renderer2d.prototyp.engine.graph;

import java.util.function.Consumer;

/**
 * Created by Justin on 04.09.2016.
 */
public class Node implements Comparable<Node> {

    @Override
    public int compareTo(Node o) {
        return 0;
    }

    /**
    * process node and all child nodes
     *
     * @param consumer instance of consumer callback
     * @param processGroupNodes flag, if group nodes should also be consumed
    */
    public void processRecursive (Consumer<Node> consumer, boolean processGroupNodes) {
        consumer.accept(this);
    }

}
