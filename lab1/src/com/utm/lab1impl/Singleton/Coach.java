package com.utm.lab1impl.Singleton;

import com.utm.lab1impl.AbstractFactory.Staff;
import com.utm.lab2impl.Adapter.Employee;
import com.utm.lab2impl.Bridge.GymAtendee;
import com.utm.lab3impl.Mediator.Mediator;
import com.utm.lab3impl.State.FreeState;
import com.utm.lab3impl.State.State;

public class Coach extends Staff implements Employee, GymAtendee {
    private static Coach instance = null;

    public String cardNumber;
    public String IBAN;
    public String IDNP;
    private State state;
    public boolean onVacation = false;
    public Integer customers = 0;

    Mediator mediator;
    public Integer highDemandRate;
    public String firstName;
    public boolean yelledAt = false;
    public String lastName;
    public String email;
    public String phoneNumber;
    public Integer salary;
    public Integer subordinates;

    public Coach() {
        firstName = "Ivan";
        lastName = "Culicov";
        email = "iculicov@mail.ru";
        phoneNumber = "+404404404";
        salary = 1200;
        subordinates = 5;

        this.state = new FreeState(this);
        highDemandRate = 100;
    }

    public void changeState(State state){
        this.state = state;
    }

    public static Coach getInstance() {
        if (instance == null)
            instance = new Coach();
        return instance;
    }

    @Override
    public void getNumberOfSubordinates() {
        System.out.println("I am a Coach, I have " + subordinates + " subordinates.");
    }

    @Override
    public String toString() {
        return "Coach{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", subordinates=" + subordinates +
                '}';
    }

    @Override
    public String getPayed(double amount) {
        return "I got "+amount+" more $ for my coaching services this month.";
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void askForBonus(){
        mediator.askForBonus(300);
    }

    @Override
    public String getJobTitle() {
        return "Coach";
    }

    @Override
    public void wereYelledAt(boolean b) {
        yelledAt = b;
    }

    @Override
    public void workout() {
        System.out.println("I have some spare time so I'll work out a bit.");
    }

    public State getState(){
        return state;
    }
}
