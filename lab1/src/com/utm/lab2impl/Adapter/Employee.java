package com.utm.lab2impl.Adapter;

import com.utm.lab3impl.Mediator.Mediator;

public interface Employee {
    String getPayed(double amount);
    void setMediator(Mediator mediator);
    String getJobTitle();
    void wereYelledAt(boolean b);
}
