package org.alan.model;

import java.util.Arrays;

public abstract class Field {

    private String[][] field;
    private static final String VEHICLE_MARK = "1";
    private static final String EMPTY_MARK = "0";
    public Field(int rowLength, int colLength){
        field = new String[rowLength][colLength];
        var row = new String[rowLength];
        Arrays.fill(row, EMPTY_MARK);
        field[0] = row;
        for (int i=0; i < rowLength;i++) {
            field[i] = Arrays.copyOf(row, colLength);
        }
    }

    public void moveVehicleInField(Vehicle vehicle) {
        int x = vehicle.getCurrentPosX();
        int y = vehicle.getCurrentPosY();
        var move = vehicle.getMovementForCurrentDirection();
        field[y][x] = EMPTY_MARK;
        field[y + move.getMoveY()][x + move.getMoveX()] = VEHICLE_MARK;
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
