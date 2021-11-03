package com.example.froggeroop.util.cor;


import com.example.froggeroop.models.GameModel;
import com.example.froggeroop.util.exceptions.YoggerException;

/**
 * Expert is the abstract class of the chain-of-responsibility design pattern.
 * An expert is an object that "Solve" a situation, if the expert detect that
 * it is not able to solve this situation, it calls the next expert recursively.
 * This means that the chain-of-responsiblity has a structure similar to a linked list.
 */
public abstract class Expert {

    private Expert next;

    public Expert() {
        setNext(null);
    }

    /**
     * Set next expert of this expert
     *
     * @param next expert
     */
    public void setNext(Expert next) {
        this.next = next;
    }


    /**
     * Recursive method that will go through the chain-of-responsibility,
     * analyzing for each expert if the expert can solve the situation and solve it
     * if it is the case.
     *
     * @param g the game object from which the expert will analyze the situation
     */
    public void detect(GameModel g) throws YoggerException {
        if (this.canSolve(g)) {
            this.Solve(g);
        } else {
            if (next != null) {
                next.detect(g);
            }
        }
    }

    /**
     * This method analyze if the situation can be solved
     *
     * @param g the game object from which the expert will analyze the situation
     * @return a boolean, true if the expert can solve this situation, false if not
     */
    abstract boolean canSolve(GameModel g);


    /**
     * @param g the game object from which the expert will analyze the situation
     */
    abstract void Solve(GameModel g) throws YoggerException;

    @Override
    public String toString() {
        return "Expert{" + getClass().getName() + "," +
                "next=" + next +
                '}';
    }
}

