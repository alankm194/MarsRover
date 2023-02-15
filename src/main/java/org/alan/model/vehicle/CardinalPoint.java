package org.alan.model.vehicle;

import java.util.Map;

public enum CardinalPoint {
    NORTH(0, "N", " ↑"),
    EAST(1, "E", " →"),
    SOUTH(2, "S", " ↓"),
    WEST(3, "W", " ←");

    private static final String LEFT_TURN = "L";
    private static final String RIGHT_TURN = "R";
    private static final int TURN_LEFT = -1;
    private static final int TURN_RIGHT = 1;

    private static final Map<Integer, CardinalPoint> orderToCardinalPointMap;

    private static final Map<String, CardinalPoint> initialToCardinalPoint;

    static {
        orderToCardinalPointMap = Map.of(
                0, NORTH,
                1, EAST,
                2, SOUTH,
                3, WEST);
        initialToCardinalPoint = Map.of(
                "N", NORTH,
                "E", EAST,
                "S", SOUTH,
                "W", WEST
        );
    }

    private static final int TOTAL_CARDINAL_POINTS = 4;

    private final int order;
    private final String initial;

    private final String arrow;

    CardinalPoint(int order, String initial, String arrow) {
        this.order = order;
        this.initial = initial;
        this.arrow = arrow;
    }
    public CardinalPoint getNewDirection(String turn) throws IllegalArgumentException {
        int turnDirection = 0;
        if (turn.equals(LEFT_TURN)) {
            turnDirection = TURN_LEFT;
        } else if(turn.equals(RIGHT_TURN)) {
            turnDirection = TURN_RIGHT;
        }
        if (turnDirection == 0) {
            throw new IllegalArgumentException("input is not a legal direction to turn.");
        }
        return orderToCardinalPointMap.get(getNewDirectionKey(turnDirection));
    }

    public String getInitial(){
        return initial;
    }
    private int getNewDirectionKey(int turnDirection) {
        return ((order + turnDirection)
                % TOTAL_CARDINAL_POINTS + TOTAL_CARDINAL_POINTS)
                % TOTAL_CARDINAL_POINTS;
    }

    public String getArrow() {return arrow;}

    public static CardinalPoint getCardinalDirectionFromInitial(String initial) {
        return initialToCardinalPoint.get(initial);
    }
}
