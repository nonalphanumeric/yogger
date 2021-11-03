package com.example.froggeroop.terrain;

/**
 * Sidewalk is a special type of lane, they are the starting point of Yog (and ending point in non-infinite mode)
 * No entities other than Yog exist on sidewalks
 */
public class Sidewalk extends Lane {


    /**
     * Instantiates a new Sidewalk.
     *
     * @param lineSize the line size
     */
    public Sidewalk(int lineSize) {
        super(lineSize);
    }
}
