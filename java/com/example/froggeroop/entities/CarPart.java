package com.example.froggeroop.entities;

import com.example.froggeroop.util.RenderPriority;

/**
 * Entity-type class for the individual parts of the cars
 */
public class CarPart extends Entity {
    /**
     * Instantiates a new Car part.
     */
    public CarPart() {
        super();
        this.setRenderPriority(RenderPriority.HIGH);
    }
}
