package com.example.froggeroop.entities;

import com.example.froggeroop.util.RenderPriority;

/**
 * Entity-type class for the individual parts of the logs
 */
public class LogPart extends Entity {
    /**
     * Instantiates a new Log part.
     */
    public LogPart() {
        super();
        this.setRenderPriority(RenderPriority.LOW);
    }

}
