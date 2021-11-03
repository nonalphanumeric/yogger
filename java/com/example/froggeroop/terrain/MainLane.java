package com.example.froggeroop.terrain;

import com.example.froggeroop.entities.Coin;
import com.example.froggeroop.entities.Mine;
import com.example.froggeroop.models.OptionModel;
import com.example.froggeroop.terrain.buffers.BufferLane;
import com.example.froggeroop.util.Density;
import com.example.froggeroop.util.Direction;
import com.example.froggeroop.util.Observator;
import com.example.froggeroop.util.Speed;

import java.util.Random;

/**
 * MainLane are abstract objects from which WaterLane and DrivableLane inherits
 */
public abstract class MainLane extends TimeAwareLane implements Observator {

    /**
     * The constant LOW_SPEED.
     */
    public static int LOW_SPEED;
    /**
     * The constant MEDIUM_SPEED.
     */
    public static int MEDIUM_SPEED;
    /**
     * The constant HIGH_SPEED.
     */
    public static int HIGH_SPEED;
    private final Direction direction;
    private final Density density;
    private final Speed speed;
    private BufferLane bufferLane;
    private int valSpeed;

    /**
     * Instantiates a new Main lane.
     *
     * @param lineSize  the line size
     * @param direction the direction
     * @param density   the density
     * @param speed     the speed
     */
    public MainLane(int lineSize, Direction direction, Density density, Speed speed) {
        super(lineSize);
        generateSpecials();
        this.direction = direction;
        this.density = density;
        this.speed = speed;

        this.setLapCounter(0);

        switch (this.speed) {
            case SLOW -> this.valSpeed = MainLane.LOW_SPEED;
            case NORMAL -> this.valSpeed = MainLane.MEDIUM_SPEED;
            case FAST -> this.valSpeed = MainLane.HIGH_SPEED;
        }
    }

    /**
     * Generate specials entities on the lane
     */
    public void generateSpecials() {
        Random rnd = new Random();

        int coinProbability = 0;

        switch (OptionModel.getDifficulty()) {
            case EASY -> coinProbability = 16;
            case MEDIUM -> coinProbability = 32;
            case HARD -> coinProbability = 64;
        }

        for (Cell c : this.getCells()) {
            if (rnd.nextInt() % coinProbability == 0) {
                c.addOnCell(new Coin());
            }
            if (this instanceof DrivableLane) {
                if (rnd.nextInt() % 46 == 0) {
                    c.addOnCell(new Mine());
                }
            }
        }
    }

    /**
     * Make things bound to move on the lane move on cell further
     */
    public abstract void advance();

    /**
     * Method executed when the lane receive a signal from the timer
     * Will make the lane execute its advance method at some frequency
     * depending on its speed attribute
     */
    public void receiveNotif() {
        this.incLapCounter();
        if (this.getLapCounter() % this.valSpeed == 0) {
            this.advance();
        }
    }

    @Override

    public String toString() {
        return "DrivableLane{" +
                "direction=" + direction +
                ", density=" + density +
                ", speed=" + speed +
                '}';
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }


    /**
     * Gets buffer lane.
     *
     * @return the buffer lane
     */
    public BufferLane getBufferLane() {
        return bufferLane;
    }

    /**
     * Sets buffer lane.
     *
     * @param bufferLane the buffer lane
     */
    public void setBufferLane(BufferLane bufferLane) {
        this.bufferLane = bufferLane;
    }


}
