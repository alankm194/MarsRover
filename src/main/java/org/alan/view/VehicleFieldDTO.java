package org.alan.view;


import java.util.Arrays;

public record VehicleFieldDTO(int[] fieldDimensions,
                              int[] vehiclePosition,
                              String vehicleFacing,
                              String[] movementInstructions) {

    @Override
    public String toString() {
        return String.format("""
                        Field dimensions: %d,%d
                        Vehicle starting position: %d,%d
                        Vehicle cardinal direction: %s
                        movement Instructions: %s""",
                fieldDimensions[0], fieldDimensions[1],
                vehiclePosition[0], vehiclePosition[1],
                vehicleFacing,
                Arrays.toString(movementInstructions)
        );
    }
}
