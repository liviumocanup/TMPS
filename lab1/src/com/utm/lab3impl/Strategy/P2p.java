package com.utm.lab3impl.Strategy;

import com.utm.lab1impl.Singleton.Coach;
import com.utm.lab2impl.Adapter.Employee;
import com.utm.lab2impl.Facade.PaymentFacade;

public class P2p implements PayStrategy {
    private Coach coach;
    private String name;
    private String cardNumber;

    @Override
    public void paymentDetails(String name, String cardNumber) {
        this.name = name;
        this.coach = findByName(name);
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int paymentAmount, Employee customer) {
        PaymentFacade paymentFacade = new PaymentFacade();
        paymentFacade.payP2P(coach, customer,paymentAmount);
    }

    private Coach findByName(String name){
        System.out.println("Found coach by name: "+ name);
        return Coach.getInstance();
    }
}
