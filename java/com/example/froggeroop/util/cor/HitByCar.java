package com.example.froggeroop.util.cor;

import com.example.froggeroop.entities.CarPart;
import com.example.froggeroop.entities.Entity;
import com.example.froggeroop.models.GameModel;
import com.example.froggeroop.terrain.Cell;
import com.example.froggeroop.util.exceptions.DeathException;

/**
 * Expert managing the player getting hit by a CarPart entity situation
 */
public class HitByCar extends Expert {
    @Override
    boolean canSolve(GameModel g) {
        Cell currentCell = g.getYog().getCellOn();
        if (currentCell != null) {
            for (Entity e : currentCell.getOnCell()) {
                if (e instanceof CarPart) return true;
            }
        }
        return false;
    }

    @Override
    void Solve(GameModel g) throws DeathException {
        throw new DeathException();
    }
}
