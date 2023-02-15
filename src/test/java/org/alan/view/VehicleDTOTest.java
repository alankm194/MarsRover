package org.alan.view;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleDTOTest {
    @Test
    public void testVehicleDTOGetStringTest() {
        var dto = new VehicleFieldDTO(
                new int[]{7, 7},
                new int[]{4, 6},
                "W",
                List.of("M", "M", "L", "R", "M"),
                "Mars Rover",
                "Circle"
        );
        assertEquals(
                """
                        Field dimensions: 7,7
                        Vehicle starting position: 4,6
                        Vehicle cardinal direction: W
                        Movement instructions: [M, M, L, R, M]
                        Vehicle type: Mars Rover
                        Field Type: Circle
                        """,
                dto.toString());
    }
}
