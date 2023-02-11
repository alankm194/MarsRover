package org.alan.model;

import java.util.Map;

public enum CardinalPoint {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private static final String LEFT_TURN = "L";
    private static final String RIGHT_TURN = "R";
    private static final int TURN_LEFT = -1;
    private static final int TURN_RIGHT = 1;

    private static final Map<Integer, CardinalPoint> orderToCardinalPointMap;

    static {
        orderToCardinalPointMap = Map.of(
                0, NORTH,
                1, EAST,
                2, SOUTH,
                3, WEST);
    }

    private static final int TOTAL_CARDINAL_POINTS = 4;

    private final int order;

    CardinalPoint(int order) {
        this.order = order;
    }
    public CardinalPoint getNewDirection(String turn) {
        int turnDirection = 0;
        if (turn.equals(LEFT_TURN)) {
            turnDirection = TURN_LEFT;
        } else if(turn.equals(RIGHT_TURN)) {
            turnDirection = TURN_RIGHT;
        }
        if (turnDirection == 0) {
            return null;
        }
        var newOrderKey = ((order + turnDirection)
                % TOTAL_CARDINAL_POINTS + TOTAL_CARDINAL_POINTS)
                % TOTAL_CARDINAL_POINTS;
        return orderToCardinalPointMap.get(newOrderKey);
    }
}
