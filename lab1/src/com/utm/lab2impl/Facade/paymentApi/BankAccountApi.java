package com.utm.lab2impl.Facade.paymentApi;

public class BankAccountApi {
    private final String name;

    public BankAccountApi(String name) {
        this.name = name;
    }

    public void notifyBank() {
        System.out.println("Bank " + name + " notified.");
    }
}
