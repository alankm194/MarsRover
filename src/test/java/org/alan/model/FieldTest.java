package org.alan.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    @Test
    public void testCreateNewField() {
        var rowLength = 5;
        var colLength = 5;
        var expected = create2dArrayAndFill(rowLength, colLength);
        Field field = new RectangleField(rowLength, colLength);
        assertArrayEquals(expected, field.getField());
    }

    @Test
    public void testPlaceVehicleOnField() {
        Field field = new RectangleField(5, 5);
        Vehicle testVehicle = new MarsRover();
        int yPos = 3;
        int xPos = 2;
        field.setVehicleInField(testVehicle, xPos, yPos);
        var fieldWithVehicle = field.getField();
        assertEquals("1", fieldWithVehicle[field.invertYCoordinate(yPos)][xPos]);
    }

    @Test
    public void testMoveVehicleInField() {
        String[][] expected = create2dArrayAndFill(5, 5);
        expected[2][3] = "1";
        Field field = new RectangleField(5, 5);
        Vehicle testVehicle = new MarsRover();
        field.setVehicleInField(testVehicle, 2, 2);
        testVehicle.setCurrentFacingPosition(CardinalPoint.EAST);
        field.moveVehicleInField(testVehicle);
        assertEquals(3, testVehicle.getCurrentPosX());
        assertEquals(2, testVehicle.getCurrentPosY());

        assertArrayEquals(expected, field.getField());
    }

    @Test
    public void testVehicleOutOfBoundsCheck() {
        String[][] expected = create2dArrayAndFill(5, 5);
        int vehicleXPos, vehicleYPos;
        vehicleYPos = vehicleXPos = 4;
        expected[vehicleYPos][vehicleXPos] = "1";
        Field field = new RectangleField(5, 5);
        Vehicle testVehicle = new MarsRover();
        field.setVehicleInField(testVehicle, 4, 4);
        testVehicle.setCurrentFacingPosition(CardinalPoint.EAST);
        var isCrash = field.checkIfVehicleWillCrash(testVehicle);
        assertTrue(isCrash);
    }

    private String[][] create2dArrayAndFill(int rowLength, int colLength) {
        var row = new String[rowLength];
        Arrays.fill(row, "0");
        var filledArray = new String[rowLength][colLength];
        filledArray[0] = row;
        for (int i = 0; i < rowLength; i++) {
            filledArray[i] = Arrays.copyOf(row, colLength);
        }
        return filledArray;
    }


}
