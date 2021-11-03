package com.example.froggeroop.util;

import java.util.ArrayList;

/**
 * Observable objects from Observator design pattern
 */
public class Observable {
    /**
     * A list of all the observators
     */
    private final ArrayList<Observator> listObservator = new ArrayList<>();

    /**
     * Instantiates a new Observable.
     */
    public Observable() {
    }

    /**
     * Add a new observator to the observable
     *
     * @param obs a Observator type object
     */
    public void add(Observator obs) {
        listObservator.add(obs);
    }

    /**
     * Send a signal to all observators, making them execute their receiveNotif method
     */
    public void notif() {
        for (Observator observator : this.getAllObservator()) {
            observator.receiveNotif();
        }
    }

    /**
     * Get the list of all observator
     *
     * @return an ArrayList of Observator type objects
     */
    public ArrayList<Observator> getAllObservator() {
        return this.listObservator;
    }

    /**
     * Get a cloned list of all observator so that modification can be made on a dummy list
     * while it is being run through to avoid concurrentModificationException
     *
     * @return a cloned ArrayList of Observator type objects
     */
    public ArrayList<Observator> getClonedObservator() {
        return (ArrayList<Observator>) this.listObservator.clone();
    }

}
