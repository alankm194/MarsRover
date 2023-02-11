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
        var stringArray = new String[rowLength][colLength];
        var row = new String[rowLength];
        Arrays.fill(row, EMPTY_MARK);
        stringArray[0] = row;
        for (int i=0; i < rowLength;i++) {
            stringArray[i] = Arrays.copyOf(row, colLength);
        }
        return stringArray;
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

    public void moveVehicleInField(Vehicle vehicle) {
        int x = vehicle.getCurrentPosX();
        int y = vehicle.getCurrentPosY();
        var move = vehicle.getMovementForCurrentDirection();
        field[y][x] = EMPTY_MARK;
        field[y + move.moveY()][x + move.moveX()] = VEHICLE_MARK;
    }

    public void setVehicleInField(Vehicle vehicle, int xPos, int yPos) {
        field[yPos][xPos] = VEHICLE_MARK;
        vehicle.setCurrentPosX(xPos);
        vehicle.setCurrentPosY(yPos);
    }

    public String[][] getField() {
        return field;
    }


}
