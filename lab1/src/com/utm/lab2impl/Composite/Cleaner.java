package com.utm.lab2impl.Composite;

import com.utm.lab2impl.Adapter.Employee;
import com.utm.lab3impl.Mediator.Mediator;

public class Cleaner implements Employee {
    Mediator mediator;

    public boolean yelledAt=false;
    @Override
    public String getPayed(double amount) {
        return "Only "+ amount+"??? Where's the rest?";
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void clean(){
        mediator.clean();
    }

    @Override
    public String getJobTitle() {
        return "Cleaner";
    }

    @Override
    public void wereYelledAt(boolean b) {
        yelledAt = b;
    }
}
