package com.utm.lab3impl.Template;

import com.utm.lab1impl.PrototypeAndBuilder.Customer;
import com.utm.lab1impl.Singleton.Coach;
import com.utm.lab2impl.Adapter.Employee;

public abstract class PaymentTemplate {
    Coach toCoach;

    PaymentTemplate(){}

    boolean verifyCustomerCredentials(String name, String cardNumber) {
        return !name.isBlank() && !cardNumber.isBlank();
    }

    protected void makingConnection(Customer from, Employee to) {
        System.out.println("Making connection...");
        System.out.println("Success!");
    }

    protected void reduceFunds(Customer customer) {
        System.out.println("Funds withdrawn from sender.");
    }

    protected void transferFunds(Employee employee, double amount) {
        notifyBank();
        System.out.println(employee.getPayed(amount));
    }

    protected void notifyBank(){
        System.out.println("Bank notified!");
    }

    public abstract void makePayment(Customer customer, double amount);
}
