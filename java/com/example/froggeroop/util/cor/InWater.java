package com.example.froggeroop.util.cor;

import com.example.froggeroop.entities.Entity;
import com.example.froggeroop.entities.LogPart;
import com.example.froggeroop.models.GameModel;
import com.example.froggeroop.terrain.Cell;
import com.example.froggeroop.terrain.WaterLane;
import com.example.froggeroop.util.exceptions.DeathException;

/**
 * Expert managing the player falling into water situation
 */
public class InWater extends Expert {
    @Override
    boolean canSolve(GameModel g) {
        Cell currentCell = g.getYog().getCellOn();
        if (currentCell != null) {
            if (!(currentCell.getOnLane() instanceof WaterLane)) return false;
            for (Entity e : currentCell.getOnCell()) {

                if (e instanceof LogPart) return false;
            }
        }

        return true;
    }

    @Override
    void Solve(GameModel g) throws DeathException {

        throw new DeathException();
    }
}
