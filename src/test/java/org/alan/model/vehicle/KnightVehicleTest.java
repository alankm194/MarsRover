package org.alan.model.vehicle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightVehicleTest {

    @ParameterizedTest
    @CsvSource({"NORTH,-1,-2", "SOUTH,1,2", "EAST,2,-1", "WEST,-2,1"})
    public void GivenKnightRoverFacesCurrentDirection_returnAcceptedMovementsForDirection(String direction, int x, int y) {
        Vehicle test = new KnightVehicle();
        test.setCurrentFacingPosition(CardinalPoint.valueOf(direction));
        var expected = new Movement(x, y);
        assertEquals(expected, test.getMovementForCurrentDirection());
    }
}
