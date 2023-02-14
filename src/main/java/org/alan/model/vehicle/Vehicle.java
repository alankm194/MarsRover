package org.alan.model.vehicle;


public abstract class Vehicle {

    private final String name;
    private int currentPosX;
    private int currentPosY;
    private CardinalPoint currentFacingPosition;
    public Vehicle(String name, CardinalPoint currentFacingPosition){
        this.currentFacingPosition = currentFacingPosition;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getCurrentPosX() {
        return currentPosX;
    }

    public int getCurrentPosY() {
        return currentPosY;
    }

    public void setCurrentPosX(int currentPosX) {
        this.currentPosX = currentPosX;
    }

    public void setCurrentPosY(int currentPosY) {
        this.currentPosY = currentPosY;
    }

    public CardinalPoint getCurrentFacingPosition() {
        return currentFacingPosition;
    }

    public void setCurrentFacingPosition(CardinalPoint currentFacingPosition) {
        this.currentFacingPosition = currentFacingPosition;
    }

    public abstract Movement getMovementForCurrentDirection();

}
