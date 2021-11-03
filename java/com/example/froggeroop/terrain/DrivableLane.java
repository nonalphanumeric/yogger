package com.example.froggeroop.terrain;

import com.example.froggeroop.entities.CarPart;
import com.example.froggeroop.entities.Entity;
import com.example.froggeroop.terrain.buffers.BufferCar;
import com.example.froggeroop.util.Density;
import com.example.froggeroop.util.Direction;
import com.example.froggeroop.util.Observator;
import com.example.froggeroop.util.Speed;

/**
 * DrivableLane are Lane objects on which CarPart circulates
 */
public class DrivableLane extends MainLane implements Observator {


    /**
     * Instantiates a new Drivable lane.
     *
     * @param lineSize  the line size
     * @param direction the direction
     * @param density   the density
     * @param speed     the speed
     */
    public DrivableLane(int lineSize, Direction direction, Density density, Speed speed) {
        super(lineSize, direction, density, speed);
        this.setBufferLane(new BufferCar(density));
    }


    /**
     * this method makes the CarPart entities on the DrivableLane move one cell further
     */
    public void advance() {
        switch (this.getDirection()) {
            case LEFT -> {
                for (int i = 0; i < this.getLineSize(); i++) {
                    CarPart cp = null;
                    for (Entity e : this.getCells().get(i).getOnCell()) {
                        if (e instanceof CarPart) cp = (CarPart) e;
                    }


                    if (cp != null) {
                        if (i == 0)
                            cp.moveFromCell(this.getCells().get(i), null);
                        else
                            cp.moveFromCell(this.getCells().get(i), this.getCells().get(i - 1));
                    }

                }
                if (!this.getBufferLane().getWaitingEntities().isEmpty()) {
                    this.getCells().get(this.getLineSize() - 1).addOnCell(
                            new CarPart()
                    );
                    this.getBufferLane().getWaitingEntities().remove(0);


                }
            }

            case RIGHT -> {
                for (int i = this.getLineSize() - 1; i >= 0; i--) {
                    CarPart cp = null;
                    for (Entity e : this.getCells().get(i).getOnCell()) {
                        if (e instanceof CarPart) cp = (CarPart) e;
                    }
                    if (cp != null) {
                        if (i == this.getLineSize() - 1)
                            cp.moveFromCell(this.getCells().get(i), null);
                        else
                            cp.moveFromCell(this.getCells().get(i), this.getCells().get(i + 1));
                    }
                }
                if (!this.getBufferLane().getWaitingEntities().isEmpty()) {
                    this.getCells().get(0).addOnCell(
                            new CarPart()
                    );
                    this.getBufferLane().getWaitingEntities().remove(0);
                }
            }

        }

    }

}
