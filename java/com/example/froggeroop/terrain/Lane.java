package com.example.froggeroop.terrain;

import java.util.ArrayList;

/**
 * Generic Lane object that are characterized by being a collection of cells
 * Lanes can be chained (having reference to neighboring lanes)
 */
public class Lane {
    private final ArrayList<Cell> cells;
    private final int lineSize;
    private Lane upLane = null;
    private Lane downLane = null;

    /**
     * Instantiates a new Lane.
     *
     * @param lineSize the line size
     */
    public Lane(int lineSize) {
        cells = new ArrayList<>();
        this.lineSize = lineSize;

        for (int i = 0; i < this.lineSize; i++) {
            Cell c = new Cell();
            c.setOnLane(this);
            cells.add(c);
        }
    }

    /**
     * Get back the cells making up the lane as an ArrayList
     *
     * @return an ArrayList of Cell objects
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }


    /**
     * Get back the number of cells making up the lane
     *
     * @return an integer
     */
    public int getLineSize() {
        return lineSize;
    }

    /**
     * Get back the chained lane on the UP direction
     *
     * @return a Lane object
     */
    public Lane getUpLane() {
        return upLane;
    }


    /**
     * Set up the chained lane on the UP direction
     *
     * @param upLane a Lane object
     */
    public void setUpLane(Lane upLane) {
        this.upLane = upLane;
    }

    /**
     * Get back the chained lane on the DOWN direction
     *
     * @return a Lane object
     */
    public Lane getDownLane() {
        return downLane;
    }


    /**
     * Get back the chained lane on the DOWN direction
     *
     * @param downLane the down lane
     */
    public void setDownLane(Lane downLane) {
        this.downLane = downLane;
    }
}
