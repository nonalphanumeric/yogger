package com.example.froggeroop.terrain.buffers;

import com.example.froggeroop.util.Density;


/**
 * The type Buffer log.
 */
public class BufferLog extends BufferLane {
    /**
     * Instantiates a new Buffer log.
     *
     * @param d the density associated with the MainLane
     */
    public BufferLog(Density d) {
        super(d);
        switch (this.getDensity()) {
            case LOW -> this.setValDensity(25);
            case MEDIUM -> this.setValDensity(20);
            case HIGH -> this.setValDensity(15);
        }

    }
}

