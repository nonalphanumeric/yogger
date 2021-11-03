package com.example.froggeroop.terrain.buffers;

import com.example.froggeroop.entities.Entity;
import com.example.froggeroop.terrain.TimeAwareLane;
import com.example.froggeroop.util.Density;
import com.example.froggeroop.util.Observator;

import java.util.ArrayList;
import java.util.Random;


/**
 * Abstract class that characterize a buffer, a three-cell lane whose job is to prepare a car or a log
 * to take places on its corresponding lane. The density of the corresponding lane dictates the frequency
 * on which the buffer fills up.
 */
public abstract class BufferLane extends TimeAwareLane implements Observator {

    private final ArrayList<Entity> waitingEntities;
    private final Density density;
    private int valDensity;


    /**
     * Constructor of a BufferLane, will automatically fills up with a car/log
     * at construction
     *
     * @param density density of the corresponding lane
     */
    public BufferLane(Density density) {
        super(3);
        this.waitingEntities = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i <= rnd.nextInt(3); i++) {
            this.getWaitingEntities().add(new Entity());
        }
        this.density = density;

    }

    /**
     * Get back the list of the waiting entities inside the buffer.
     *
     * @return an ArrayList of entities
     */
    public ArrayList<Entity> getWaitingEntities() {
        return waitingEntities;
    }

    /**
     * Get back the density of the BufferLane
     *
     * @return an enum-type characterizing the density of the lane
     */
    public Density getDensity() {
        return density;
    }

    /**
     * Set the density of the BufferLane
     *
     * @param valDensity an enum-type characterizing the density of the lane
     */
    public void setValDensity(int valDensity) {
        this.valDensity = valDensity;
    }

    /**
     * Implementation of receiveNotif's observator method.
     * At each signal received from the timer, the buffer will
     * increments its laps counter, using the latter, it will know
     * if it will fill up with entities, but only if it is empty.
     */
    @Override
    public void receiveNotif() {
        this.incLapCounter();
        if (this.getLapCounter() % this.valDensity == 0) {
            if (this.getWaitingEntities().isEmpty()) {
                Random rnd = new Random();
                for (int i = 0; i < rnd.nextInt(3); i++) {
                    this.getWaitingEntities().add(new Entity());
                }

            }
        }
    }
}
