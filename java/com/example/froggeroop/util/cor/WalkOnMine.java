package com.example.froggeroop.util.cor;

import com.example.froggeroop.entities.Entity;
import com.example.froggeroop.entities.Mine;
import com.example.froggeroop.models.GameModel;
import com.example.froggeroop.terrain.Cell;
import com.example.froggeroop.util.exceptions.DeathException;

/**
 * This expert manages the situation where the player walks over a mine
 */
public class WalkOnMine extends Expert {
    @Override
    boolean canSolve(GameModel g) {
        Cell currentCell = g.getYog().getCellOn();
        if (currentCell != null) {
            for (Entity e : currentCell.getOnCell()) {
                if (e instanceof Mine) return true;
            }
        }
        return false;
    }

    @Override
    void Solve(GameModel g) throws DeathException {
        throw new DeathException();
    }
}
