package com.example.froggeroop.entities;

import com.example.froggeroop.util.RenderPriority;

/**
 * Entity-type class for the mines, trap like entities that can be kill the player if walked over.
 */
public class Mine extends Entity {

    /**
     * Instantiates a new Mine.
     */
    public Mine() {
        this.setRenderPriority(RenderPriority.LOW);
    }
}
