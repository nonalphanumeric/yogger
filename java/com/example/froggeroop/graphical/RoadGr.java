package com.example.froggeroop.graphical;

import javafx.scene.image.Image;

/**
 * Graphical Entity abstract class for DrivableLane graphics
 */
public final class RoadGr {

    private static final Image image = new Image("images/road.png");

    /**
     * Gets image.
     *
     * @return the image
     */
    public static Image getImage() {
        return image;
    }
}
