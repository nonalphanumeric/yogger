package com.example.froggeroop.util;

import com.example.froggeroop.entities.Entity;

import java.util.Comparator;

/**
 * Comparator of Entity objects, used automatically when sorting a list of Entity objects.
 */
public class EntityComparator implements Comparator<Entity> {
    @Override
    public int compare(Entity o1, Entity o2) {
        return o1.compareTo(o2);
    }

    /**
     * Provide a reversed order
     *
     * @param o1 the first entity
     * @param o2 the second entity
     * @return reversed sign value of compareTo
     */
    @SuppressWarnings("unused")
    public int reversed(Entity o1, Entity o2) {
        return o1.compareTo(o2) * (-1);
    }

}
