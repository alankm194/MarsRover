package org.alan.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTest {

    @Test
    public void testCreateNewField() {
        var rowLength = 5;
        var colLength = 5;
        var row = new String[rowLength];
        Arrays.fill(row, "0");
        var expected = new String[rowLength][colLength];
        expected[0] = row;
        for (int i=0; i < rowLength;i++) {
            expected[i] = Arrays.copyOf(row, colLength);
        }
        Field field = new RectangleField(rowLength, colLength);
        assertArrayEquals(expected, field.getField());
    }

    @Test
    public void testPlaceVehicleOnField() {
        Field field = new RectangleField(5, 5);
        Vehicle testVehicle = new MarsRover();
        field.setVehicleInField(testVehicle, 2, 3);
        var fieldWithVehicle = field.getField();
        assertEquals(testVehicle.getName(), fieldWithVehicle[2][3]);
    }


}
