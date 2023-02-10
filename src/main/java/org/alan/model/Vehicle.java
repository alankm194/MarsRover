package org.alan.model;

public abstract class Vehicle {

    public String name;

    public Vehicle(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
