package com.utm.lab1impl.AbstractFactory;

public class GymCoordinator extends Staff {
    @Override
    public void getNumberOfSubordinates() {
        System.out.println("I am a Coordinator, everyone is my subordinate.");
    }

    @Override
    public String toString() {
        return "GymCoordinator{}";
    }
}
