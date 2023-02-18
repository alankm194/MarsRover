package org.alan.view;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleFieldDTO that = (VehicleFieldDTO) o;
        return Arrays.equals(fieldDimensions, that.fieldDimensions) && Arrays.equals(vehiclePosition, that.vehiclePosition) && vehicleFacing.equals(that.vehicleFacing) && movementInstructions.equals(that.movementInstructions) && vehicleType.equals(that.vehicleType) && fieldType.equals(that.fieldType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(vehicleFacing, movementInstructions, vehicleType, fieldType);
        result = 31 * result + Arrays.hashCode(fieldDimensions);
        result = 31 * result + Arrays.hashCode(vehiclePosition);
        return result;
    }
}
