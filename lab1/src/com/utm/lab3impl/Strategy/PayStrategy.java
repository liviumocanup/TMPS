package com.utm.lab3impl.Strategy;

import com.utm.lab2impl.Adapter.Employee;

public interface PayStrategy {
    void pay(int paymentAmount, Employee employee);
    void paymentDetails(String name, String cardNumber);
}
