package com.utm.lab3impl.State;

import com.utm.lab1impl.Singleton.Coach;

public class HighDemandState extends State{
    HighDemandState(Coach coach, int payOffer){
        if(payOffer > coach.highDemandRate){
            coach.changeState(new FullState(coach));
        }else {
            System.out.println("Right now I can't take you in as my client.");
        }
    }

    @Override
    public String free(){
        coach.changeState(new FreeState(coach));
    }

    @Override
    public String full(){

    }

    @Override
    public String free(){
        coach.changeState(new FreeState(coach));
    }
}
