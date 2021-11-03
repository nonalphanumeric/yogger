package com.example.froggeroop.terrain;

import com.example.froggeroop.models.OptionModel;
import com.example.froggeroop.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Road characterize all the lanes on which the player can moves. It contains
 * a WindowList object to manage the set of visible lanes over the set of all lanes.
 */
public class Road {
    private Sidewalk beginSidewalk, endSidewalk;
    private WindowList<Lane> allLanes;


    /**
     * Constructor of the class, actually calls the reset method
     */
    public Road() {

        this.reset();
    }


    /**
     * Intialize the road, taking into consideration the mode
     */
    public void reset() {

        ArrayList<Lane> tmp = new ArrayList<>();
        this.beginSidewalk = new Sidewalk(16);
        tmp.add(beginSidewalk);


        Random rnd = new Random();

        /*setting speeds according to difficulty*/
        switch (OptionModel.getDifficulty()) {
            case EASY -> {
                MainLane.HIGH_SPEED = 4;
                MainLane.MEDIUM_SPEED = 5;
                MainLane.LOW_SPEED = 6;
            }
            case MEDIUM -> {
                MainLane.HIGH_SPEED = 3;
                MainLane.MEDIUM_SPEED = 4;
                MainLane.LOW_SPEED = 5;
            }
            case HARD -> {
                MainLane.HIGH_SPEED = 2;
                MainLane.MEDIUM_SPEED = 3;
                MainLane.LOW_SPEED = 4;
            }
        }


        for (int i = 0; i < 5; i++) {
            tmp.add(new DrivableLane(16, Direction.values()[rnd.nextInt(2)]
                    , Density.values()[rnd.nextInt(3)]
                    , Speed.values()[rnd.nextInt(3)]));
        }
        for (int i = 0; i < 5; i++) {
            Direction d = i % 2 == 0 ? Direction.LEFT : Direction.RIGHT;

            tmp.add(new WaterLane(16, d
                    , Density.values()[rnd.nextInt(3)]
                    , Speed.values()[rnd.nextInt(3)]));
        }

        if (OptionModel.getInfinite()) {
            tmp.add(new DrivableLane(16, Direction.values()[rnd.nextInt(2)]
                    , Density.values()[rnd.nextInt(3)]
                    , Speed.values()[rnd.nextInt(3)]));
        } else tmp.add(new Sidewalk(16));

        for (Lane l : tmp) {
            try {
                Lane nextLane = tmp.get(tmp.indexOf(l) + 1);
                l.setDownLane(nextLane);
                nextLane.setUpLane(l);
            } catch (IndexOutOfBoundsException e) {
            }

        }

        this.allLanes = new WindowList<>(tmp, tmp.get(0), 12);

    }


    /**
     * Sets end sidewalk.
     *
     * @param endSidewalk the end sidewalk
     */
    public void setEndSidewalk(Sidewalk endSidewalk) {
        this.endSidewalk = endSidewalk;
    }


    /**
     * Gets begin sidewalk.
     *
     * @return the begin sidewalk
     */
    public Sidewalk getBeginSidewalk() {
        return beginSidewalk;
    }


    /**
     * Gets visible lanes.
     *
     * @return the visible lanes
     */
    public List<Lane> getVisibleLanes() {
        return allLanes.getWindow();
    }

    /**
     * Gets all lanes.
     *
     * @return the all lanes
     */
    public WindowList<Lane> getAllLanes() {
        return allLanes;
    }

    /**
     * Add a new lane to the road, depending on the number of lanes, will add a WaterLane or a DrivableLane
     * so that a block of 4 WaterLane directly follows a block of 4 DrivableLane
     * Consecutive WaterLanes always face different directions
     */
    public void addLane() {
        Random rnd = new Random();


        Lane newD;
        if (this.getAllLanes().getItems().size() % 10 < 5) {
            newD = new DrivableLane(16, Direction.values()[rnd.nextInt(2)]
                    , Density.values()[rnd.nextInt(3)]
                    , Speed.values()[rnd.nextInt(3)]);
        } else {
            Direction d = this.getAllLanes().getItems().size() % 2 == 0 ? Direction.RIGHT : Direction.LEFT;
            newD = new WaterLane(16, d
                    , Density.values()[rnd.nextInt(3)]
                    , Speed.values()[rnd.nextInt(3)]);
        }
        Lane lastOne = this.getAllLanes().getItems().get(this.getAllLanes().getItems().size() - 1);

        lastOne.setDownLane(newD);
        newD.setUpLane(lastOne);
        this.getAllLanes().addToItems(newD);
    }

    /**
     * Make all the visible lanes of the road observant of a timer
     *
     * @param timer the observable timer
     */
    public void addToTimer(Timer timer) {
        timer.getAllObservator().clear();
        for (Lane l : this.getAllLanes().getWindow()) {
            if (l instanceof MainLane l2) {
                timer.add(l2);
                timer.add(l2.getBufferLane());
            }
        }
    }
}
