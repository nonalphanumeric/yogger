package com.example.froggeroop.util.cor;

import com.example.froggeroop.models.GameModel;
import com.example.froggeroop.util.exceptions.DeathException;

/**
 * Expert managing the situation where the player isn't on the road anymore.
 * This is expected if the player is on a log that goes out of the screen.
 */
public class OutOfGrid extends Expert {
    @Override
    boolean canSolve(GameModel g) {
        return g.getYog().getCellOn() == null;
    }

    @Override
    void Solve(GameModel g) throws DeathException {
        throw new DeathException();
    }
}
