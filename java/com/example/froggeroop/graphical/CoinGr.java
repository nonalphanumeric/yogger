package com.example.froggeroop.graphical;

import javafx.scene.image.Image;

/**
 * Graphical Entity abstract class for Coin graphics
 */
public final class CoinGr {
    private static final Image image = new Image("images/coin2.png");

    /**
     * Gets image.
     *
     * @return the image
     */
    public static Image getImage() {
        return image;
    }
}
