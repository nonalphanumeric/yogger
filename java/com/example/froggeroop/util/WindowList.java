package com.example.froggeroop.util;

import java.util.List;

/**
 * Special data structure that manages a list of items and a sublist of this list. The
 * sublist, of a fixed length, can move forward or backward over the list
 *
 * @param <T> the type of object making up the list
 */
public class WindowList<T> {
    private List<T> window;
    private List<T> items;
    private int windowSize;
    private int indexFstEl;

    /**
     * Instantiates a new Window list.
     *
     * @param origin       the origin
     * @param firstElement the first element
     * @param windowSize   the window size
     */
    public WindowList(List<T> origin, T firstElement, int windowSize) {
        this.setItems(origin);
        this.setWindowSize(windowSize);
        this.setIndexFstEl(this.getItems().indexOf(firstElement));
        this.setWindow(items.subList(this.getIndexFstEl(), this.getIndexFstEl() + windowSize));
    }

    /**
     * Make the sublist move forward if it has not reached end of the list
     */
    public void rollUp() {
        if (canRollUp()) {
            setIndexFstEl(getIndexFstEl() + 1);
            this.setWindow(items.subList(this.getIndexFstEl(), this.getIndexFstEl() + windowSize));
        }

    }

    /**
     * Check if the sublist has not reached the end of list yet
     *
     * @return True if the sublist has reached the end of the list, False otherwise
     */
    public boolean canRollUp() {
        return (getIndexFstEl() + windowSize < getItems().size());
    }

    /**
     * Make the sublist move backward if it has not reached end of the list
     */
    public void rollDown() {
        if (canRollDown()) {
            setIndexFstEl(getIndexFstEl() - 1);
            this.setWindow(items.subList(this.getIndexFstEl(), this.getIndexFstEl() + windowSize));
        }
    }

    /**
     * Check if the sublist has not reached the beginning of list yet
     *
     * @return True if the sublist has reached the beginning of the list, False otherwise
     */
    public boolean canRollDown() {
        return !(getIndexFstEl() == 0);
    }

    /**
     * Add a new item to the list
     *
     * @param t Object to add to the list
     */
    public void addToItems(T t) {
        getItems().add(t);
    }

    /**
     * Gets window.
     *
     * @return the window
     */
    public List<T> getWindow() {
        return window;
    }

    /**
     * Sets window.
     *
     * @param window the window
     */
    public void setWindow(List<T> window) {
        this.window = window;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * Gets window size.
     *
     * @return the window size
     */
    public int getWindowSize() {
        return windowSize;
    }

    /**
     * Sets window size.
     *
     * @param windowSize the window size
     */
    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    /**
     * Gets index for the first element
     *
     * @return the first element's index
     */
    public int getIndexFstEl() {
        return indexFstEl;
    }

    /**
     * Sets index of first element of the window.
     *
     * @param indexFstEl the first element's index
     */
    public void setIndexFstEl(int indexFstEl) {
        this.indexFstEl = indexFstEl;
    }


    /**
     * Rewind the window to make it go back at the beginning of the list
     */
    public void rewind() {
        this.setIndexFstEl(0);
        this.setWindow(items.subList(this.getIndexFstEl(), this.getIndexFstEl() + windowSize));
    }
}
