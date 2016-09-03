package com.jukusoft.renderer2d.prototyp.engine.recycler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Justin on 03.09.2016.
 */
public class BasicRecycler {

    /**
    * max size of objects in recycler
    */
    protected static final int MAX_SIZE_PER_TYPE = 50;

    /**
    * map with objects
    */
    protected static Map<Class<? extends Recycleable>,List<Recycleable>> objectMap = new ConcurrentHashMap<>();

    /**
    * recycle object
     *
     * @param obj instance of object
    */
    public static <T extends Recycleable> void recycle (T obj) {
        //get list with objects of this class
        List<Recycleable> list = objectMap.get(obj.getClass());

        //check, if list doesnt exists
        if (list == null) {
            //create new list and put to map
            list = new ArrayList<>();
            objectMap.put(obj.getClass(), list);
        }

        //cleanUp object first
        obj.cleanUp();

        if (list.size() > MAX_SIZE_PER_TYPE) {
            //dont recycle this object
        } else {
            //recycle object
            synchronized (list) {
                list.add(obj);
            }
        }
    }

}
