package org.alan.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {

    @Test
    public void testCreateMarsRoverVehicle() {
        Vehicle testRover = new MarsRover();
        assertEquals("Mars Rover", testRover.getName());
    }
}
