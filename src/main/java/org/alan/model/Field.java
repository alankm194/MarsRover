package org.alan.model;

import java.util.Arrays;

public abstract class Field {

    private String[][] field;

    public Field(int rowLength, int colLength){
        field = new String[rowLength][colLength];
        var row = new String[rowLength];
        Arrays.fill(row, "0");
        field[0] = row;
        for (int i=0; i < rowLength;i++) {
            field[i] = Arrays.copyOf(row, colLength);
        }
    }

    public void setVehicleInField(Vehicle vehicle, int xPos, int yPos) {
        field[xPos][yPos] = vehicle.getName();
        vehicle.setCurrentPosX(xPos);
        vehicle.setCurrentPosY(yPos);
    }

    public String[][] getField() {
        return field;
    }
}
