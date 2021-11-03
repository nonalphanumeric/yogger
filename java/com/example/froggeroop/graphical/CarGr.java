package com.example.froggeroop.graphical;

import javafx.scene.image.Image;

/**
 * Graphical Entity abstract class for CarPart graphics
 */
public final class CarGr {

    private static final Image image = new Image("images/car2.png");

    /**
     * Get back the image of the CarPart
     *
     * @return a JavaFX image
     */
    public static Image getImage() {
        return image;
    }
}