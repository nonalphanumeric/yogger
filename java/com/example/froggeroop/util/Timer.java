package com.example.froggeroop.util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


/**
 * Timer is an Observable type object which sends signals at some frequency.
 */
public class Timer extends Observable {

    private int lapsCounter;
    private boolean suspended;


    /**
     * Constructor of Timer object
     *
     * @param laps the interval of time in seconds at which the Timer will send signal
     */
    public Timer(double laps) {
        KeyFrame frameJeu = new KeyFrame(Duration.seconds(laps), new EventTimer());
        Timeline timeline = new Timeline(frameJeu);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    /**
     * The type Event timer.
     */
    class EventTimer implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            notif();
        }
    }

    /**
     * Increment the timer lap counter
     */
    public void incCounter() {
        this.setLapsCounter(this.getLapsCounter() + 1);
    }

    /**
     * See if the Timer is suspended
     *
     * @return a Boolean characterizing the suspended state of the Timer
     */
    public boolean isSuspended() {
        return suspended;
    }

    /**
     * Set the suspended state of the Timer, if True, the Timer will stop sending signals to its
     * observators
     *
     * @param suspended a Boolean characterizing the suspended state of the Timer
     */
    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    @Override
    public void notif() {
        this.incCounter();
        if (!isSuspended()) {

            for (Observator observator : this.getClonedObservator()) {
                observator.receiveNotif();
            }
        }

    }

    /**
     * Gets laps counter.
     *
     * @return the laps counter
     */
    public int getLapsCounter() {
        return lapsCounter;
    }

    /**
     * Sets laps counter.
     *
     * @param lapsCounter the laps counter
     */
    public void setLapsCounter(int lapsCounter) {
        this.lapsCounter = lapsCounter;
    }
}
