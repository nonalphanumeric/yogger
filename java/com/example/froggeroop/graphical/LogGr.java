package com.example.froggeroop.graphical;

import javafx.scene.image.Image;

/**
 * Graphical Entity abstract class for LogPart graphics
 */
public final class LogGr {

    private static final Image image = new Image("images/log.png");

    /**
     * Gets image.
     *
     * @return the image
     */
    public static Image getImage() {
        return image;
    }
}