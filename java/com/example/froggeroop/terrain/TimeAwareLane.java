package com.example.froggeroop.terrain;

/**
 * parent class of all lanes observant of the timer
 */
public abstract class TimeAwareLane extends Lane {
    /**
     * The constant MAXCNT.
     */
    public final static int MAXCNT = 9999;
    /**
     * lapCounter attributes is a tool used by TimeAwareLane objects to perform
     * things at a fixed frequency.
     * For example, if the Timer object send signals every seconds, and the TimeAwareLane
     * should execute something every three seconds, it only has to check if the value
     * of lapCounter (which is incremented at signal of Timer) % 3 == 0
     */
    private int lapCounter = 0;

    /**
     * Instantiates a new Time aware lane.
     *
     * @param lineSize the line size
     */
    public TimeAwareLane(int lineSize) {
        super(lineSize);

    }

    /**
     * Gets lap counter.
     *
     * @return the lap counter
     */
    public int getLapCounter() {
        return lapCounter;
    }

    /**
     * Sets lap counter.
     *
     * @param lapCounter the lap counter
     */
    public void setLapCounter(int lapCounter) {
        this.lapCounter = lapCounter;
    }

    /**
     * Increments to lapCounter, using MAXCNT to avoid overflow
     */
    public void incLapCounter() {
        setLapCounter((this.getLapCounter() + 1) % TimeAwareLane.MAXCNT);
    }
}
