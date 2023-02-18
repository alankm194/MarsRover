package org.alan.model.field;

import org.alan.model.vehicle.CardinalPoint;
import org.alan.model.vehicle.Movement;
import org.alan.model.vehicle.Vehicle;

public interface Model {
    void moveVehicleInField(Vehicle vehicle);

    CardinalPoint changeVehicleDirection(Movement movement, CardinalPoint currentDirection);

    boolean checkIfVehicleWillCrash(Vehicle vehicle);

    String[][] getField();

    int invertYCoordinate(int yPos);

    String getFieldLocationAt(int x, int y);

    void setFinalVehicleLocation(int finalX, int finalY);

    void setStartingVehicleLocation(int finalX, int finalY);

    boolean checkPositionIsOutOfBound(int xPos, int yPos);

    void setVehicleInField(Vehicle vehicle, int xPos, int yPos);
}
