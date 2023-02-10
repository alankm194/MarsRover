package org.alan.model;

public abstract class Vehicle {

    private final String name;
    private int currentPosX;
    private int currentPosY;

    public Vehicle(String name){
        this.name = name;
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


    public String getName(){
        return name;
    }
}
