package com.example.froggeroop.entities;

import com.example.froggeroop.util.RenderPriority;

/**
 * Entity for the player's character.
 */
public class Yog extends Entity {

    /**
     * Instantiates a new Yog.
     */
    public Yog() {
        this.setRenderPriority(RenderPriority.MEDIUM);
    }
}
