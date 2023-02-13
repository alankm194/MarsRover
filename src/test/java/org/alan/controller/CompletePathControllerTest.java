package org.alan.controller;

import org.alan.model.vehicle.CardinalPoint;
import org.alan.model.vehicle.KnightVehicle;
import org.alan.model.vehicle.MarsRover;
import org.alan.model.vehicle.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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

    @Test
    public void testControllerIgnoresCommandsThatLeadsOutOfBounds() {
        int lengthX = 3;
        int lengthY = 3;
        CompletePathController controller = new CompletePathController();
        controller.setField(lengthX, lengthY);
        Vehicle vehicle = new MarsRover();
        vehicle.setCurrentFacingPosition(CardinalPoint.EAST);
        var movementInstructions = List.of("M", "M", "M", "M");
        String finalLocation = controller.getFinalLocationForVehicle(vehicle, movementInstructions, 0, 0);
        assertEquals("2 0 E", finalLocation);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/completeControllerKnightVehicleTest.csv", numLinesToSkip = 1)
    public void testKnightVehicle(String currDir, String expectedDir, int expectedXPos, int expectedYPos) {
        int lengthX = 15;
        int lengthY = 15;
        CardinalPoint expectedDirection = CardinalPoint.valueOf(expectedDir);
        CompletePathController controller = new CompletePathController();
        controller.setField(lengthX, lengthY);
        Vehicle vehicle = new KnightVehicle();
        vehicle.setCurrentFacingPosition(CardinalPoint.valueOf(currDir));
        var movementInstructions = List.of("M");
        String finalLocation = controller.getFinalLocationForVehicle(vehicle, movementInstructions, 5, 5);
        assertEquals(String.format("%d %d %s", expectedXPos, expectedYPos, expectedDirection.getInitial()),
                finalLocation);

    }

}
