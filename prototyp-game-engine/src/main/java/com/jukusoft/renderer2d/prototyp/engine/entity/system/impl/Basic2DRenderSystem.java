package com.jukusoft.renderer2d.prototyp.engine.entity.system.impl;

import com.jukusoft.renderer2d.prototyp.engine.entity.EntityComponentSystem;
import com.jukusoft.renderer2d.prototyp.engine.entity.component.Image;
import com.jukusoft.renderer2d.prototyp.engine.entity.component.Position2D;
import com.jukusoft.renderer2d.prototyp.engine.entity.system.RenderSystem;

import java.util.List;

/**
 * Created by Justin on 04.09.2016.
 */
public class Basic2DRenderSystem implements RenderSystem {

    @Override
    public void render(EntityComponentSystem ecs) {
        //get entities which can be rendered
        List<Long> imageEntities = ecs.findEntitiesWithComponent(Image.class);

        //iterate through entities
        for (long entityID : imageEntities) {
            //get position component
            Position2D position = ecs.getComponent(entityID, Position2D.class);

            //get image component
            Image image = ecs.getComponent(entityID, Image.class);

            float x = 0;
            float y = 0;

            if (position != null) {
                x += position.getX();
                y += position.getY();
            }

            //add relative position of image
            x += image.getRelX();
            y += image.getRelY();

            //TODO: add image to scene graph
        }
    }

}
