package com.utm.lab3impl.State;

import com.utm.lab1impl.Singleton.Coach;

public class VacationState extends State{
    public VacationState(Coach coach) {
        super(coach);
        coach.onVacation = true;
        coach.customers = 0;
        coach.highDemandRate = 100;
    }

    @Override
    public void free() {
        coach.onVacation = false;
        coach.changeState(new FreeState(coach));
        System.out.println("Back to working...");
    }

    @Override
    public void highDemand(int payOffer) {
        coach.changeState(new HighDemandState(coach));
        System.out.println("Back to working, a lot of customers were waiting.");
    }

    @Override
    public void vacation() {
        System.out.println("I'm still on vacation. Call me later.");
    }
}
