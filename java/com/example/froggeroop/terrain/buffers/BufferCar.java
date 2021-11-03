package com.example.froggeroop.terrain.buffers;

import com.example.froggeroop.util.Density;


/**
 * Child-class of BufferLane that contains density value proper to BufferLane associated to BufferLane
 */
public class BufferCar extends BufferLane {

    /**
     * Instantiates a new Buffer car.
     *
     * @param d the density associated with the MainLane
     */
    public BufferCar(Density d) {
        super(d);
        switch (this.getDensity()) {
            case LOW -> this.setValDensity(60);
            case MEDIUM -> this.setValDensity(40);
            case HIGH -> this.setValDensity(20);
        }

    }
}
