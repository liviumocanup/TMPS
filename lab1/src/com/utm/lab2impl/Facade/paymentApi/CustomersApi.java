package com.utm.lab2impl.Facade.paymentApi;

import com.utm.lab2impl.Adapter.Employee;

public class CustomersApi {
    public void makingConnection(Employee from, Employee to) {
        System.out.println("Making connection...");
        success();
    }

    public void success() {
        System.out.println("All good.");
    }
}
