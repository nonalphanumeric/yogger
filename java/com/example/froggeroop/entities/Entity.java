package com.example.froggeroop.entities;

import com.example.froggeroop.terrain.Cell;
import com.example.froggeroop.util.RenderPriority;

/**
 * Entities are the object that populates cells.
 */
public class Entity implements Comparable<Entity> {
    /**
     * The cell on which the entity is currently on.
     */
    private Cell cellOn;
    /**
     * The render priority of the entity which dictates how the entities will be drawn
     * when stacked on a cell. Entities can be sorted using their render priority.
     */
    private RenderPriority renderPriority;

    /**
     * Empty constructor of the entity class
     */
    public Entity() {
    }

    /**
     * Get back the render priority of the entity.
     *
     * @return enum -type from VERY_LOW to VERY_HIGH
     */
    public RenderPriority getRenderPriority() {
        return renderPriority;
    }

    /**
     * Set the render priority of the entity
     *
     * @param renderPriority enum-type from VERY_LOW to VERY_HIGH
     */
    public void setRenderPriority(RenderPriority renderPriority) {
        this.renderPriority = renderPriority;
    }

    /**
     * Get back the cell on which the entity is currently on
     *
     * @return a cell object
     */
    public Cell getCellOn() {
        return cellOn;
    }

    /**
     * Set the cell on which the entity is
     *
     * @param cellOn the cell on which the entity will be
     */
    public void setCellOn(Cell cellOn) {
        this.cellOn = cellOn;
    }

    /**
     * Moves the entity from one cell to another.
     *
     * @param source      The cell on which the entity is
     * @param destination The cell on which the entity will be
     */
    public void moveFromCell(Cell source, Cell destination) {
        if (source != null)
            source.removeFromCell(this);
        if (destination != null) {
            destination.addOnCell(this);
            setCellOn(destination);
        } else setCellOn(null);
    }


    /**
     * Implementation of compareTo's method from comparable interface
     *
     * @param o the entity compared to the "this" entity
     * @return a negative integer, zero, or a positive integer if the render priority
     * of the entity is less important, equals or greater than the compared entity's render priority
     */
    @Override
    public int compareTo(Entity o) {
        return this.getRenderPriority().compareTo(o.getRenderPriority());
    }
}
