package com.utm.lab3impl.State;

import com.utm.lab1impl.Singleton.Coach;

public abstract class State {
    Coach coach;

    State(Coach coach){
        this.coach = coach;
    }

    public abstract String free();
    public abstract String highDemand();
    public abstract String full();

    public abstract String vacation();
}
