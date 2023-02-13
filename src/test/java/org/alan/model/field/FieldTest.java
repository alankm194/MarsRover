package org.alan.model.field;

import org.alan.model.vehicle.CardinalPoint;
import org.alan.model.vehicle.MarsRover;
import org.alan.model.vehicle.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    @ParameterizedTest
    @CsvSource({"2,5", "7,3", "5,5"})
    public void testCreateNewField(int rowLength, int colLength) {
        Field field = new RectangleField(rowLength, colLength);
        String[][] fieldArray = field.getField();
        assertEquals(fieldArray.length, colLength);
        Arrays.stream(fieldArray).forEach(arr -> assertEquals(arr.length, rowLength));
        for (String[] row: fieldArray) {
            for (String element: row) {
                assertEquals("0", element);
            }
        }
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

    private static String[][] create2dArrayAndFill(int rowLength, int colLength){
        var stringArray = new String[colLength][rowLength];
        for (String[] row: stringArray)
            Arrays.fill(row, "0");
        return stringArray;
    }


}
