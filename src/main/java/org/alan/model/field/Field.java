package org.alan.model.field;

import org.alan.model.vehicle.CardinalPoint;
import org.alan.model.vehicle.Movement;
import org.alan.model.vehicle.Vehicle;

import java.util.Arrays;

public abstract class Field {

    private final String[][] field;
    protected static final String VEHICLE_MARK = " 1";
    protected static final String INBOUND_MARK = " 0";

    protected static final String OUT_OF_BOUNDS_MARK = "-1";


    public Field(int rowLength, int colLength) {
        field = createField(rowLength, colLength);
    }

    private String[][] createField(int rowLength, int colLength) {
        var stringArray = new String[rowLength][colLength];
        for (String[] row : stringArray) {
            Arrays.fill(row, OUT_OF_BOUNDS_MARK);
        }
        return stringArray;
    }

    public void moveVehicleInField(Vehicle vehicle) {
        if (!checkIfVehicleWillCrash(vehicle)) {
            int x = vehicle.getCurrentPosX();
            int y = vehicle.getCurrentPosY();
            var move = vehicle.getMovementForCurrentDirection();
            CardinalPoint currentFace = vehicle.getCurrentFacingPosition();
            field[y][x] = INBOUND_MARK;
            field[y + move.moveY()][x + move.moveX()] = VEHICLE_MARK;
            vehicle.setCurrentPosX(vehicle.getCurrentPosX() + move.moveX());
            vehicle.setCurrentPosY(vehicle.getCurrentPosY() + move.moveY());
            vehicle.setCurrentFacingPosition(changeVehicleDirection(move, currentFace));
        }
    }

    public CardinalPoint changeVehicleDirection(Movement movement, CardinalPoint currentDirection) {
        return switch (currentDirection) {
            case NORTH, SOUTH -> {
                if (movement.moveX() > 0) {
                    yield CardinalPoint.EAST;
                } else if (movement.moveX() < 0) {
                    yield CardinalPoint.WEST;
                }
                yield currentDirection;

            }
            case EAST, WEST -> {
                if (movement.moveY() > 0) {
                    yield CardinalPoint.SOUTH;
                } else if (movement.moveY() < 0) {
                    yield CardinalPoint.NORTH;
                }
                    yield currentDirection;
            }

        };
    }

    public boolean checkIfVehicleWillCrash(Vehicle vehicle) {
        var movement = vehicle.getMovementForCurrentDirection();
        int currYPos = vehicle.getCurrentPosY();
        int nextYPos = currYPos + movement.moveY();

        if (nextYPos >= field.length || 0 > nextYPos) {
            return true;
        }
        int currXPos = vehicle.getCurrentPosX();
        int nextXPos = currXPos + movement.moveX();
        return nextXPos >= field[currYPos].length || 0 > nextXPos;
    }


    public void setVehicleInField(Vehicle vehicle, int xPos, int yPos) {
        vehicle.setCurrentPosX(xPos);
        vehicle.setCurrentPosY(invertYCoordinate(yPos));
        field[invertYCoordinate(yPos)][xPos] = VEHICLE_MARK;
    }

    public String[][] getField() {
        return field;
    }

    public int invertYCoordinate(int yPos) {
        return Math.abs(yPos - field.length) - 1;
    }

    protected abstract void fillInboundField();

}
