package com.example.froggeroop.terrain;

import com.example.froggeroop.entities.Entity;
import com.example.froggeroop.entities.LogPart;
import com.example.froggeroop.entities.Yog;
import com.example.froggeroop.terrain.buffers.BufferLog;
import com.example.froggeroop.util.Density;
import com.example.froggeroop.util.Direction;
import com.example.froggeroop.util.Observator;
import com.example.froggeroop.util.Speed;

/**
 * WaterLane are Lane on which LogPart circulates
 */
public class WaterLane extends MainLane implements Observator {


    /**
     * Instantiates a new Water lane.
     *
     * @param lineSize  the line size
     * @param direction the direction
     * @param density   the density
     * @param speed     the speed
     */
    public WaterLane(int lineSize, Direction direction, Density density, Speed speed) {
        super(lineSize, direction, density, speed);
        this.setBufferLane(new BufferLog(density));
    }

    /**
     * this method makes the LogPart and Yog entities on the WaterLane move one cell further
     */
    public void advance() {
        switch (this.getDirection()) {
            case LEFT -> {
                for (int i = 0; i < this.getLineSize(); i++) {
                    LogPart lp = null;
                    for (Entity e : this.getCells().get(i).getOnCell()) {
                        if (e instanceof LogPart) lp = (LogPart) e;
                    }
                    if (lp != null) {
                        if (i == 0) {
                            lp.moveFromCell(this.getCells().get(i), null);

                            Entity frog = null;
                            for (Entity f : this.getCells().get(i).getOnCell()) {
                                if (f instanceof Yog)
                                    frog = f;
                            }
                            if (frog != null)
                                frog.moveFromCell(this.getCells().get(i), null);

                        } else {
                            lp.moveFromCell(this.getCells().get(i), this.getCells().get(i - 1));
                            Entity frog = null;
                            for (Entity f : this.getCells().get(i).getOnCell()) {
                                if (f instanceof Yog)
                                    frog = f;
                            }
                            if (frog != null)
                                frog.moveFromCell(this.getCells().get(i), this.getCells().get(i - 1));

                        }
                    }

                }
                if (!this.getBufferLane().getWaitingEntities().isEmpty()) {
                    this.getCells().get(this.getLineSize() - 1).addOnCell(
                            new LogPart()
                    );
                    this.getBufferLane().getWaitingEntities().remove(0);


                }
            }

            case RIGHT -> {
                for (int i = this.getLineSize() - 1; i >= 0; i--) {
                    LogPart lp = null;
                    for (Entity e : this.getCells().get(i).getOnCell()) {
                        if (e instanceof LogPart) lp = (LogPart) e;
                    }
                    if (lp != null) {
                        if (i == this.getLineSize() - 1) {
                            lp.moveFromCell(this.getCells().get(i), null);

                            Entity frog = null;
                            for (Entity f : this.getCells().get(i).getOnCell()) {
                                if (f instanceof Yog)
                                    frog = f;
                            }
                            if (frog != null)
                                frog.moveFromCell(this.getCells().get(i), null);


                        } else {
                            lp.moveFromCell(this.getCells().get(i), this.getCells().get(i + 1));
                            Entity frog = null;
                            for (Entity f : this.getCells().get(i).getOnCell()) {
                                if (f instanceof Yog)
                                    frog = f;
                            }
                            if (frog != null)
                                frog.moveFromCell(this.getCells().get(i), this.getCells().get(i + 1));

                        }
                    }
                }
                if (!this.getBufferLane().getWaitingEntities().isEmpty()) {
                    this.getCells().get(0).addOnCell(
                            new LogPart()
                    );
                    this.getBufferLane().getWaitingEntities().remove(0);
                }
            }

        }
    }


}
