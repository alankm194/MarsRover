package org.alan.view;


import java.util.List;

public record VehicleFieldDTO(int[] fieldDimensions,
                              int[] vehiclePosition,
                              String vehicleFacing,
                              List<String> movementInstructions,
                              String vehicleType,
                              String fieldType) {

    @Override
    public String toString() {
        return String.format("""
                        Field dimensions: %d,%d
                        Vehicle starting position: %d,%d
                        Vehicle cardinal direction: %s
                        Movement instructions: %s
                        Vehicle type: %s
                        Field Type: %s
                        """,
                fieldDimensions[0], fieldDimensions[1],
                vehiclePosition[0], vehiclePosition[1],
                vehicleFacing,
                movementInstructions.toString(),
                vehicleType,
                fieldType
        );
    }
}
