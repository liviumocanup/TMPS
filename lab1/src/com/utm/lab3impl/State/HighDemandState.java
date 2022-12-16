package com.utm.lab3impl.State;

import com.utm.lab1impl.Singleton.Coach;

public class HighDemandState extends State{
    public HighDemandState(Coach coach){
        super(coach);
        coach.onVacation = false;
        coach.customers++;
        coach.highDemandRate = coach.highDemandRate+50;
    }

    public void highDemand(int payOffer){
        if(payOffer > coach.highDemandRate){
            System.out.println("Ok, I'll find time for one more customer.");
            coach.customers++;
        }else {
            System.out.println("Right now I can't take any more customers.");
        }
    }

    @Override
    public void free(){
        coach.customers = 0;
        coach.changeState(new FreeState(coach));
        if(coach.highDemandRate == 300){
            coach.askForBonus();
        }
        System.out.println("I'm free now. I'll accept any customer.");
    }

    @Override
    public void vacation(){
        coach.changeState(new VacationState(coach));
        System.out.println("I'm going on a vacation now. Too much stress.");
    }
}
