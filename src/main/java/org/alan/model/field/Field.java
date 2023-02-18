package org.alan.model.field;

import org.alan.model.vehicle.CardinalPoint;
import org.alan.model.vehicle.Movement;
import org.alan.model.vehicle.Vehicle;

import java.util.Arrays;

public abstract class Field  implements Model{

    private final String[][] field;
    protected static final String STARTING_VEHICLE_MARK = " 1";
    protected static final String INBOUND_MARK = " 0";
    protected static final String OUT_OF_BOUNDS_MARK = "-1";
    protected static final String FINAL_VEHICLE_LOCATION_MARK = " 2";



    public Field(int rowLength, int colLength) {
        field = createField(rowLength, colLength);
    }

    private String[][] createField(int rowLength, int colLength) {
        var stringArray = new String[rowLength][colLength];
        Arrays.stream(stringArray)
                .forEach(row -> Arrays.fill(row, OUT_OF_BOUNDS_MARK));
        return stringArray;
    }

    public void moveVehicleInField(Vehicle vehicle) {
        if (!checkIfVehicleWillCrash(vehicle)) {
            int x = vehicle.getCurrentPosX();
            int y = vehicle.getCurrentPosY();
            var move = vehicle.getMovementForCurrentDirection();
            CardinalPoint currentFace = vehicle.getCurrentFacingPosition();
            setFieldLocationAt(x, y, vehicle.getCurrentFacingPosition().getArrow());
            setFieldLocationAt(x + move.moveX(), y + move.moveY(), STARTING_VEHICLE_MARK);
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
        int currXPos = vehicle.getCurrentPosX();
        int nextXPos = currXPos + movement.moveX();
        if (nextYPos >= field.length || 0 > nextYPos ) {
            return true;
        }
        if (nextXPos >= field[currYPos].length  || 0 > nextXPos) {
            return true;
        }
        return checkPositionIsOutOfBound(nextXPos, nextYPos);
    }


    public boolean checkPositionIsOutOfBound(int xPos, int yPos) {
        return getFieldLocationAt(xPos, yPos).equals(OUT_OF_BOUNDS_MARK);
    }


    public void setVehicleInField(Vehicle vehicle, int xPos, int yPos) {
        vehicle.setCurrentPosX(xPos);
        vehicle.setCurrentPosY(invertYCoordinate(yPos));
        setFieldLocationAt(xPos, invertYCoordinate(yPos), STARTING_VEHICLE_MARK);
    }

    public String[][] getField() {
        return field;
    }

    public int invertYCoordinate(int yPos) {
        return Math.abs(yPos - field.length) - 1;
    }

    public String getFieldLocationAt(int x, int y) {
        return field[y][x];
    }

    public void setFieldLocationAt(int x, int y, String mark) {
        field[y][x] = mark;
    }

    public void setFinalVehicleLocation(int finalX, int finalY) {
        setFieldLocationAt(finalX, finalY, FINAL_VEHICLE_LOCATION_MARK);
    }

    public void setStartingVehicleLocation(int finalX, int finalY) {
        setFieldLocationAt(finalX, invertYCoordinate(finalY), STARTING_VEHICLE_MARK);
    }

    protected abstract void fillInboundField();

}
