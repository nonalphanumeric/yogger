package com.example.froggeroop.util.cor;

import com.example.froggeroop.models.GameModel;
import com.example.froggeroop.terrain.Cell;
import com.example.froggeroop.util.exceptions.VictoryException;

/**
 * Expert managing the victory of the player (by getting on the other side of the road)
 */
public class GameVictory extends Expert {
    @Override
    boolean canSolve(GameModel g) {
        Cell currentCell = g.getYog().getCellOn();
        return currentCell.getOnLane().getDownLane() == null;
    }

    @Override
    void Solve(GameModel g) throws VictoryException {
        throw new VictoryException();
    }
}
