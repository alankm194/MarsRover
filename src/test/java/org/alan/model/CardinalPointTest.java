package org.alan.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardinalPointTest {

    @ParameterizedTest
    @CsvSource({"NORTH,EAST", "EAST,SOUTH", "SOUTH,WEST", "WEST,NORTH"})
    public void GivenCardinalDirectionAndTurnRight_returnCorrectCardinal(String currentPosition, String newPosition) {
        var expected = CardinalPoint.valueOf(newPosition);
        var current = CardinalPoint.valueOf(currentPosition);
        assertEquals(expected, current.getNewDirection("R"));
    }

    @ParameterizedTest
    @CsvSource({"NORTH,WEST", "EAST,NORTH", "SOUTH,EAST", "WEST,SOUTH"})
    public void GivenCardinalDirectionAndTurnLeft_returnCorrectCardinal(String currentPosition, String newPosition) {
        var expected = CardinalPoint.valueOf(newPosition);
        var current = CardinalPoint.valueOf(currentPosition);
        assertEquals(expected,  current.getNewDirection("L"));
    }

    @ParameterizedTest
    @CsvSource({"NORTH,SOUTH", "EAST,WEST", "SOUTH,NORTH", "WEST,EAST"})
    public void GivenCardinalDirectionTurnLeftTwice_returnDirectOpposite(String currentPosition, String newPosition) {
        var expected = CardinalPoint.valueOf(newPosition);
        var current = CardinalPoint.valueOf(currentPosition);
        current = current.getNewDirection("L");
        assertEquals(expected, current.getNewDirection("L"));
    }

    @ParameterizedTest
    @CsvSource({"NORTH,SOUTH", "EAST,WEST", "SOUTH,NORTH", "WEST,EAST"})
    public void GivenCardinalDirectionTurnRightTwice_returnDirectOpposite(String currentPosition, String newPosition) {
        var expected = CardinalPoint.valueOf(newPosition);
        var current = CardinalPoint.valueOf(currentPosition);
        current = current.getNewDirection("R");
        assertEquals(expected, current.getNewDirection("R"));
    }
    @ParameterizedTest
    @CsvSource({"NORTH,NORTH", "EAST,EAST", "SOUTH,SOUTH", "WEST,WEST"})
    public void WhenNorthAndTurnLeftOnceAndRightOnce_returnSameDirection(String currentPosition, String newPosition) {
        var expected = CardinalPoint.valueOf(newPosition);
        var current = CardinalPoint.valueOf(currentPosition);
        current = current.getNewDirection("L");
        assertEquals(expected, current.getNewDirection("R"));
    }
}
