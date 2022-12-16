package com.utm.lab3impl.State;

import com.utm.lab1impl.Singleton.Coach;

public class FreeState extends State{
    public FreeState(Coach coach) {
        super(coach);
        coach.onVacation = false;
        coach.customers = 0;
    }

    @Override
    public void free() {
        System.out.println("I'm still free, but can take one more customer now.");
        coach.customers++;
    }

    @Override
    public void highDemand(int payOffer) {
        coach.changeState(new HighDemandState(coach));
        System.out.println("There are too many customers, I'll have to increase my rate per hour.");
    }

    @Override
    public void vacation() {
        coach.changeState(new VacationState(coach));
        System.out.println("Everything looks to be peaceful here, i'm going to take a vacation.");
    }
}
