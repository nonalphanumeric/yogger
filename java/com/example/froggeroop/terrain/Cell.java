package com.example.froggeroop.terrain;

import com.example.froggeroop.entities.Entity;
import com.example.froggeroop.util.EntityComparator;

import java.util.ArrayList;

/**
 * Cells are objects that populate the lanes. Entities can be stacked on them
 */
@SuppressWarnings("unused")
public class Cell {
    private ArrayList<Entity> onCell;
    private Lane onLane;


    /**
     * Instantiates a new Cell.
     */
    public Cell() {
        this.onCell = new ArrayList<>();
    }


    /**
     * Remove an entity from the cell
     *
     * @param e the entity to be removed
     */
    public void removeFromCell(Entity e) {
        getOnCell().remove(e);
        this.onCell.sort(new EntityComparator());

    }

    /**
     * Get an ArrayList of the entities currently on the cell
     *
     * @return an ArrayList of Entity object
     */
    public ArrayList<Entity> getOnCell() {
        return onCell;
    }

    /**
     * Add an entity on the cell
     *
     * @param e the Entity object to be added
     */
    public void addOnCell(Entity e) {

        getOnCell().add(e);
        e.setCellOn(this);
        this.onCell.sort(new EntityComparator());

    }

    /**
     * Get the lane on which the cell exists
     *
     * @return a Lane object
     */
    public Lane getOnLane() {
        return onLane;
    }

    /**
     * Set the lane on which the cell exists
     *
     * @param onLane the Lane object
     */
    public void setOnLane(Lane onLane) {
        this.onLane = onLane;
    }
}
