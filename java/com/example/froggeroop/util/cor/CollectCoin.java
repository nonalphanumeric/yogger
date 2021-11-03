package com.example.froggeroop.util.cor;

import com.example.froggeroop.entities.Coin;
import com.example.froggeroop.entities.Entity;
import com.example.froggeroop.models.GameModel;
import com.example.froggeroop.terrain.Cell;

/**
 * Expert managing the collecting of coins
 */
public class CollectCoin extends Expert {
    @Override
    boolean canSolve(GameModel g) {
        Cell currentCell = g.getYog().getCellOn();
        if (currentCell != null) {
            for (Entity e : currentCell.getOnCell()) {

                if (e instanceof Coin) return true;
            }
        }

        return false;
    }

    @Override
    void Solve(GameModel g) {
        Cell currentCell = g.getYog().getCellOn();
        if (currentCell != null) {
            for (Entity e : currentCell.getOnCell()) {

                if (e instanceof Coin) e.moveFromCell(currentCell, null);
            }
        }

        g.incCoins();

    }
}
