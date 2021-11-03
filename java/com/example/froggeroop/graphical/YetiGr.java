package com.example.froggeroop.graphical;

import javafx.scene.image.Image;


/**
 * Graphical Entity abstract class for Yog graphics
 */
public final class YetiGr {

    private static final Image image = new Image("images/yeti.png");

    /**
     * Gets image.
     *
     * @return the image
     */
    public static Image getImage() {
        return image;
    }
}