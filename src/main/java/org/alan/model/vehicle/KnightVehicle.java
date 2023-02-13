package org.alan.model.vehicle;

import java.util.EnumMap;

public class KnightVehicle extends Vehicle{
    private static final String NAME = "Knight Rover";

    private static final EnumMap<CardinalPoint, Movement> DIRECTION_MOVEMENT_MAP;
    static {
        DIRECTION_MOVEMENT_MAP = new EnumMap<>(CardinalPoint.class);
        DIRECTION_MOVEMENT_MAP.put(CardinalPoint.NORTH, new Movement(-1, -2));
        DIRECTION_MOVEMENT_MAP.put(CardinalPoint.EAST, new Movement(2, -1));
        DIRECTION_MOVEMENT_MAP.put(CardinalPoint.SOUTH, new Movement(1, 2));
        DIRECTION_MOVEMENT_MAP.put(CardinalPoint.WEST, new Movement(-2, 1));
    }
    public KnightVehicle() {
        super(NAME);
    }

    @Override
    public Movement getMovementForCurrentDirection() {
        return DIRECTION_MOVEMENT_MAP.get(super.getCurrentFacingPosition());
    }
}
