package com.utm.lab2impl.Bridge;

public class Receptionist implements Reception {
    private final GymAtendee gymAtendee;

    public Receptionist(GymAtendee gymAtendee) {
        this.gymAtendee = gymAtendee;
    }

    @Override
    public void checkIn() {
        System.out.println("Welcome back to the gym!");
        gymAtendee.workout();
    }
}
