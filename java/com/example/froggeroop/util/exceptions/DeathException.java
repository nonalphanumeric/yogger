package com.example.froggeroop.util.exceptions;

/**
 * Special exception to signal the death of the player.
 * In the context of Chain-of-responsibility call, the caller
 * of the COR will manage how death should be treated.
 */
public class DeathException extends YoggerException {
    public DeathException() {

    }
}
