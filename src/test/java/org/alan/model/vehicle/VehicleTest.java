package org.alan.model.vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {
    @Test
    public void testCreateMarsRoverVehicle() {
        Vehicle testRover = new MarsRover(CardinalPoint.EAST);
        assertEquals("Mars Rover", testRover.getName());
        assertEquals(CardinalPoint.EAST, testRover.getCurrentFacingPosition());

    }
    @Test
    public void testGetCurrentPosition() {
        var currentX = 23;
        var currentY = 66;
        Vehicle test = new MarsRover(CardinalPoint.EAST);
        test.setCurrentPosX(currentX);
        test.setCurrentPosY(currentY);

        assertEquals(currentX, test.getCurrentPosX());
        assertEquals(currentY, test.getCurrentPosY());
    }

    @Test
    public void testGetCurrentFacingPosition() {
        Vehicle test = new MarsRover(CardinalPoint.EAST);
        assertEquals(CardinalPoint.EAST, test.getCurrentFacingPosition());
    }




}
