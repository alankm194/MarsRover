package org.alan.model;

import java.util.Arrays;

public abstract class Field {

    private final String[][] field;
    private static final String VEHICLE_MARK = "1";
    private static final String EMPTY_MARK = "0";
    public Field(int rowLength, int colLength){
        field = createField(rowLength, colLength);
    }

    private String[][] createField(int rowLength, int colLength){
        var stringArray = new String[colLength][rowLength];
        for (String[] row: stringArray) {
            Arrays.fill(row, "0");
        }
        return stringArray;
    }
    public void moveVehicleInField(Vehicle vehicle) {
        int x = vehicle.getCurrentPosX();
        int y = vehicle.getCurrentPosY();
        var move = vehicle.getMovementForCurrentDirection();
        if (!checkIfVehicleWillCrash(vehicle)) {
            field[y][x] = EMPTY_MARK;
            field[y + move.moveY()][x + move.moveX()] = VEHICLE_MARK;
            vehicle.setCurrentPosX(vehicle.getCurrentPosX() + move.moveX());
            vehicle.setCurrentPosY(vehicle.getCurrentPosY() + move.moveY());
        }
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

}
