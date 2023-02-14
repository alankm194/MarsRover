package org.alan.model.vehicle;

import java.util.EnumMap;

public class MarsRover extends Vehicle {

    private static final String NAME = "Mars Rover";
    private static final EnumMap<CardinalPoint, Movement> DIRECTION_MOVEMENT_MAP;

    static {
        DIRECTION_MOVEMENT_MAP = new EnumMap<>(CardinalPoint.class);
        DIRECTION_MOVEMENT_MAP.put(CardinalPoint.NORTH, new Movement(0, -1));
        DIRECTION_MOVEMENT_MAP.put(CardinalPoint.EAST, new Movement(1, 0));
        DIRECTION_MOVEMENT_MAP.put(CardinalPoint.SOUTH, new Movement(0, 1));
        DIRECTION_MOVEMENT_MAP.put(CardinalPoint.WEST, new Movement(-1, 0));
    }

    public MarsRover(CardinalPoint currentFacingDirection){
        super(NAME, currentFacingDirection);
    }
    @Override
    public Movement getMovementForCurrentDirection() {
        return DIRECTION_MOVEMENT_MAP.get(super.getCurrentFacingPosition());
    }
}
