package org.alan.controller;

import org.alan.model.Field;
import org.alan.model.RectangleField;
import org.alan.model.Vehicle;

import java.util.List;

public class CompletePathController {

    private Field field;


    public void setField(int x, int y) {
        field = new RectangleField(x, y);
    }

    public String getFinalLocationForVehicle(Vehicle vehicle, List<String> movementInstructions, int startX, int startY) {
        field.setVehicleInField(vehicle, startX, startY);
        for (String instruction: movementInstructions) {
            if (instruction.equals("L") || instruction.equals("R")) {
                vehicle.setCurrentFacingPosition(vehicle.getCurrentFacingPosition().getNewDirection(instruction));
            } else if(instruction.equals("M")) {
                field.moveVehicleInField(vehicle);
            }
        }
        return String.format("%s %s %s",
                vehicle.getCurrentPosX(),
                field.invertYCoordinate(vehicle.getCurrentPosY()),
                vehicle.getCurrentFacingPosition().getInitial());
    }
}
