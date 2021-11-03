package com.example.froggeroop.graphical;

import javafx.scene.image.Image;

/**
 * Graphical Entity abstract class for SideWalk graphics
 */
public final class SidewalkGr {

    private static final Image image = new Image("images/sidewalk.png");

    /**
     * Gets image.
     *
     * @return the image
     */
    public static Image getImage() {
        return image;
    }
}