package com.example.froggeroop.entities;

import com.example.froggeroop.util.RenderPriority;


/**
 * Entity-type class for the coins, bonus that can be picked up by the player.
 */
public class Coin extends Entity {
    /**
     * Instantiates a new Coin.
     */
    public Coin() {
        super();
        this.setRenderPriority(RenderPriority.MEDIUM);
    }
}

