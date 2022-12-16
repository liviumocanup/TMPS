package com.utm.lab3impl.State;

import com.utm.lab1impl.Singleton.Coach;

public abstract class State {
    Coach coach;

    State(Coach coach){
        this.coach = coach;
    }

    public abstract void free();
    public abstract void highDemand(int payOffer);

    public abstract void vacation();
}
