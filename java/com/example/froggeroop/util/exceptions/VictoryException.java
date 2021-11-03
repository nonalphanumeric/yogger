package com.example.froggeroop.util.exceptions;

/**
 * Special exception to signal the victory of the player.
 * In the context of Chain-of-responsibility call, the caller
 * of the COR will manage how victory should be treated.
 */
public class VictoryException extends YoggerException {
    public VictoryException() {

    }
}
