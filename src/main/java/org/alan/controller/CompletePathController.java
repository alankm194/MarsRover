package org.alan.controller;

import org.alan.model.field.CircleField;
import org.alan.model.field.Model;
import org.alan.model.field.RectangleField;
import org.alan.model.vehicle.CardinalPoint;
import org.alan.model.vehicle.KnightVehicle;
import org.alan.model.vehicle.MarsRover;
import org.alan.model.vehicle.Vehicle;
import org.alan.view.Output;
import org.alan.view.VehicleFieldDTO;

import java.util.List;

public class CompletePathController implements Controller {

    private Model field;

    private static final String CIRCLE_FIELD = "Circle";
    private static final String KNIGHT_ROVER = "Knight Rover";

    @Override
    public void startController(VehicleFieldDTO vehicleFieldDTO, Output output) throws IllegalArgumentException{
        int[] fieldDimensions = vehicleFieldDTO.fieldDimensions();
        int[] vehicleStartCoordinates = vehicleFieldDTO.vehiclePosition();

        setField(fieldDimensions[0], fieldDimensions[1], vehicleFieldDTO.fieldType());
        if (field.checkPositionIsOutOfBound(vehicleStartCoordinates[0], vehicleStartCoordinates[1])) {
            throw new IllegalArgumentException("Vehicle is set in an illegal place");
        }
        CardinalPoint direction = CardinalPoint.getCardinalDirectionFromInitial(vehicleFieldDTO.vehicleFacing());
        Vehicle vehicle = createVehicle(vehicleFieldDTO.vehicleType(), direction);
        String finalLocation = getFinalLocationForVehicle(vehicle,
                vehicleFieldDTO.movementInstructions(),
                vehicleStartCoordinates[0],
                vehicleStartCoordinates[1]);

        output.outputField(field.getField(), "Output of final position of field with vehicle, starting place of vehicle is marked as 1. final position of vehicle is marked as 2.");
        output.outputAnswer(finalLocation, String.format("final Coordinates for %s", vehicleFieldDTO.vehicleType()));
    }

    private Vehicle createVehicle(String vehicleType, CardinalPoint startDirection) {
        if (vehicleType.equals(KNIGHT_ROVER)) {
            return new KnightVehicle(startDirection);
        } else {
            return new MarsRover(startDirection);
        }
    }

    void setField(int x, int y, String fieldType) {
        if (fieldType.equals(CIRCLE_FIELD)) {
            field = new CircleField(x, y);
        } else {
            field = new RectangleField(x, y);
        }
    }



    public String getFinalLocationForVehicle(Vehicle vehicle, List<String> movementInstructions, int startX, int startY) {
        field.setVehicleInField(vehicle, startX, startY);
        for (String instruction : movementInstructions) {
            if (instruction.equals("L") || instruction.equals("R")) {
                vehicle.setCurrentFacingPosition(vehicle.getCurrentFacingPosition().getNewDirection(instruction));
            } else if (instruction.equals("M")) {
                field.moveVehicleInField(vehicle);
            }
        }
        field.setStartingVehicleLocation(startX, startY);
        field.setFinalVehicleLocation(vehicle.getCurrentPosX(), vehicle.getCurrentPosY());
        return String.format("%s %s %s",
                vehicle.getCurrentPosX(),
                field.invertYCoordinate(vehicle.getCurrentPosY()),
                vehicle.getCurrentFacingPosition().getInitial());
    }
}
