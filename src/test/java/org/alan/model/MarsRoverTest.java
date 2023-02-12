package org.alan.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverTest {

    @ParameterizedTest
    @CsvSource({"NORTH,0,-1", "SOUTH,0,1", "EAST,1,0", "WEST,-1,0"})
    public void GivenMarsRoverFacesCurrentDirection_returnAcceptedMovementsForDirection(String direction, int x, int y) {
        Vehicle test = new MarsRover();
        test.setCurrentFacingPosition(CardinalPoint.valueOf(direction));
        var expected = new Movement(x, y);
        assertEquals(expected, test.getMovementForCurrentDirection());
    }
}
