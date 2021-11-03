package com.example.froggeroop.graphical;

import javafx.scene.image.Image;

/**
 * Graphical Entity abstract class for WaterLane graphics
 */
public final class WaterGr {

    private static final Image image = new Image("images/water2.gif");

    /**
     * Gets image.
     *
     * @return the image
     */
    public static Image getImage() {
        return image;
    }
}
