package com.jukusoft.renderer2d.prototyp.engine.utils;

/**
 * Created by Justin on 24.08.2016.
 */
public class LocalUniqueID {

    /**
    * id counter
    */
    protected static volatile long idCounter = 0;

    /**
    * generate new local unique id
     *
     * @return local unique id
    */
    public static long generateID () {
        return LocalUniqueID.idCounter++;
    }

}
