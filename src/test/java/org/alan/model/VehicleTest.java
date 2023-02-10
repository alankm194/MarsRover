package org.alan.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {
    @Test
    public void testCreateMarsRoverVehicle() {
        Vehicle testRover = new MarsRover();
        assertEquals("Mars Rover", testRover.getName());
    }
    @Test
    public void testGetCurrentPosition() {
        var currentX = 23;
        var currentY = 66;
        Vehicle test = new MarsRover();
        test.setCurrentPosX(currentX);
        test.setCurrentPosY(currentY);

        assertEquals(currentX, test.getCurrentPosX());
        assertEquals(currentY, test.getCurrentPosY());
    }

    @Test
    public void testGetCurrentFacingPosition() {
        Vehicle test = new MarsRover();
        test.setCurrentFacingPosition(CardinalPoint.EAST);
        assertEquals(CardinalPoint.EAST, test.getCurrentFacingPosition());
    }




}
