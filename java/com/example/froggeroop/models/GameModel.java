package com.example.froggeroop.models;

import com.example.froggeroop.entities.Yog;
import com.example.froggeroop.terrain.Cell;
import com.example.froggeroop.terrain.Lane;
import com.example.froggeroop.terrain.Road;
import com.example.froggeroop.util.Direction;
import com.example.froggeroop.util.Timer;
import com.example.froggeroop.util.cor.*;

import java.util.ArrayList;

/**
 * GameModel is a model-type module that contains the attributes and methods related to the
 * Game view.
 */
public class GameModel {
    private final Road road;
    private final Yog yog;
    private final Expert cor;


    private int score = 0;
    private int coinCollected = 0;

    /**
     * Model constructor that will initialize some objects of the game.
     */
    public GameModel() {
        this.cor = initializeChain();
        this.road = new Road();
        this.yog = new Yog();
        this.road.getBeginSidewalk().getCells().get(6).addOnCell(this.yog);
    }

    /**
     * Get back the player's entity
     *
     * @return a Yogger entity
     */
    public Yog getYog() {
        return yog;
    }


    /**
     * Move the player's entity inside the game depending on a given direction
     *
     * @param direction enum-type direction
     */
    public void moveYog(Direction direction) {
        int getCol = this.yog.getCellOn().getOnLane().getCells().indexOf(this.yog.getCellOn());
        Lane newLane = this.yog.getCellOn().getOnLane();
        Cell newCell = null;


        switch (direction) {
            case UP:
                newLane = this.yog.getCellOn().getOnLane().getUpLane();
                if (newLane != null)
                    newCell = newLane.getCells().get(getCol);
                break;
            case DOWN:
                newLane = this.yog.getCellOn().getOnLane().getDownLane();
                if (newLane != null)
                    newCell = newLane.getCells().get(getCol);
                break;
            case LEFT:
                try {
                    newCell = newLane.getCells().get(getCol - 1);
                } catch (IndexOutOfBoundsException e) {
                }
                break;
            case RIGHT:
                try {
                    newCell = newLane.getCells().get(getCol + 1);
                } catch (IndexOutOfBoundsException e) {
                }
                break;
        }

        if (newCell != null) {

            this.yog.moveFromCell(this.yog.getCellOn(), newCell);
        }

    }

    /**
     * Get back the road (collection of Lanes with bound methods) of the game.
     *
     * @return Road -type object
     */
    public Road getRoad() {
        return road;
    }

    /**
     * Add the model as observant of a timer
     *
     * @param timer an observable Timer
     */
    public void addToTimer(Timer timer) {
        this.getRoad().addToTimer(timer);
    }


    /**
     * Get back the score of the game
     *
     * @return an integer characterizing the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the score of the game
     *
     * @param score an integer characterizing the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Initialize the chain of responsibility that manages the different
     * possible situations taking place in the game
     *
     * @return The first expert of that chain
     */
    public Expert initializeChain() {
        ArrayList<Expert> tmp = new ArrayList<>() {{
            add(new HitByCar());
            add(new WalkOnMine());
            add(new InWater());
            add(new OutOfGrid());
            add(new CollectCoin());
            add(new GameVictory());
        }};

        for (int i = 0; i < tmp.size() - 1; i++) {
            tmp.get(i).setNext(tmp.get(i + 1));
        }

        return tmp.get(0);
    }

    /**
     * Increments the number of coins collected by the player.
     */
    public void incCoins() {
        this.coinCollected += 1;
    }

    /**
     * Get back the number of coins the player has collected.
     *
     * @return integer characterizing the number of coins collected.
     */
    public int getCoinCollected() {
        return coinCollected;
    }

    /**
     * Get back the first expert of the chain of responsibility.
     *
     * @return an Expert-type object.
     */
    public Expert getCor() {
        return cor;
    }

    /**
     * Manage the score of the game, requires to know the number of laps from the beginning of the game.
     * Depending on the mode, the score is managed differently
     *
     * @param laps number of laps from the main Timer.
     */
    public void manageScore(int laps) {
        if (OptionModel.getInfinite()) {
            int scr = (this.getRoad().getAllLanes().getItems().indexOf(
                    (this.getYog().getCellOn().getOnLane())) * 10)
                    + (this.getCoinCollected() * 50);
            if (scr > this.getScore()) this.setScore(scr);
        } else {
            this.setScore((int) (1000 - (1000 * ((-1 / (((float) laps / 500 + 1)) + 1)))
                    + (this.getCoinCollected() * 50)));
        }
    }


    /**
     * Used in infinite-mode, this method will move the visible lanes one lane further.
     *
     * @param timerForLanes Timer-type that send signals to the visible lane.
     */
    public void scroll(Timer timerForLanes) {
        if (this.getRoad().getVisibleLanes().indexOf(this.getYog().getCellOn().getOnLane()) == 5) {
            if (!this.getRoad().getAllLanes().canRollUp()) {
                this.getRoad().addLane();
            }
            this.getRoad().getAllLanes().rollUp();
            this.addToTimer(timerForLanes);

        } else if (this.getRoad().getVisibleLanes().indexOf(this.getYog().getCellOn().getOnLane()) < 3) {
            if (this.getRoad().getAllLanes().canRollDown()) {
                this.getRoad().getAllLanes().rollDown();
                this.addToTimer(timerForLanes);
            }
        }
    }

}
