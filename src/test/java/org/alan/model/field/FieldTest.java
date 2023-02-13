package org.alan.model.field;

import org.alan.model.vehicle.CardinalPoint;
import org.alan.model.vehicle.MarsRover;
import org.alan.model.vehicle.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.alan.model.field.Field.INBOUND_MARK;
import static org.alan.model.field.Field.VEHICLE_MARK;
import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    @ParameterizedTest
    @CsvSource({"2,5", "7,3", "5,5"})
    public void testCreateNewField(int rowLength, int colLength) {
        Field field = new RectangleField(rowLength, colLength);
        String[][] fieldArray = field.getField();
        assertEquals(fieldArray.length, rowLength);
        Arrays.stream(fieldArray).forEach(arr -> assertEquals(arr.length, colLength));
        for (String[] row: fieldArray) {
            for (String element: row) {
                assertEquals(INBOUND_MARK, element);
            }
        }
    }

    @Test
    public void testPlaceVehicleOnField() {
        Field field = new RectangleField(5, 5);
        Vehicle testVehicle = new MarsRover();
        int yPos = 0;
        int xPos = 0;
        field.setVehicleInField(testVehicle, xPos, yPos);
        assertEquals(VEHICLE_MARK, field.getFieldLocationAt(xPos, field.invertYCoordinate(yPos)));
    }

    @Test
    public void testMoveVehicleInField() {
        String[][] expected = create2dArrayAndFill(5, 7);
        expected[1][3] = VEHICLE_MARK;
        Field field = new RectangleField(5, 7);
        Vehicle testVehicle = new MarsRover();
        field.setVehicleInField(testVehicle, 2, 3);
        testVehicle.setCurrentFacingPosition(CardinalPoint.EAST);
        field.moveVehicleInField(testVehicle);
        assertEquals(3, testVehicle.getCurrentPosX());
        assertEquals(1, testVehicle.getCurrentPosY());
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
        var stringArray = new String[rowLength][colLength];
        for (String[] row: stringArray)
            Arrays.fill(row, INBOUND_MARK);
        return stringArray;
    }

    @Test
    public void testGetFieldLocationAtField() {
        Field field = new RectangleField(7, 8);
        int x = 3;
        int y = 5;
        field.getField()[y][x] = INBOUND_MARK;
        assertEquals(INBOUND_MARK, field.getFieldLocationAt(x, y));
    }

    @Test
    public void testSetFieldLocationAtField() {
        Field field = new RectangleField(7, 8);
        int x = 3;
        int y = 5;
        field.setFieldLocationAt(x, y, INBOUND_MARK);
        assertEquals(INBOUND_MARK, field.getFieldLocationAt(x, y));
    }


}
