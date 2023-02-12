package org.alan.controller;

import org.alan.model.CardinalPoint;
import org.alan.model.MarsRover;
import org.alan.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompletePathControllerTest {

    @Test
    public void testControllerForACompletePath() {
        int lengthX = 7;
        int lengthY = 6;
        CompletePathController controller = new CompletePathController();
        controller.setField(lengthX, lengthY);
        Vehicle vehicle = new MarsRover();
        vehicle.setCurrentFacingPosition(CardinalPoint.EAST);
        var movementInstructions = List.of("M", "M", "L", "M", "M" , "R", "M", "M");
        String finalLocation = controller.getFinalLocationForVehicle(vehicle, movementInstructions, 0, 0);
        assertEquals("4 2 E", finalLocation);
    }
}
