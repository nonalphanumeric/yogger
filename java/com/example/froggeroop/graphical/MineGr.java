package com.example.froggeroop.graphical;

import javafx.scene.image.Image;

/**
 * Graphical Entity abstract class for the Mine graphics
 */
public class MineGr {
    private static final Image image = new Image("images/mine.png");

    /**
     * Gets image.
     *
     * @return the image
     */
    public static Image getImage() {
        return image;
    }
}
