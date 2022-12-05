package com.utm.lab2impl.Proxy;

import com.utm.lab1impl.Singleton.Coach;
import com.utm.lab2impl.Adapter.Employee;

public class ProxyPaymentCreation implements DistributeSalary {
    private final Employee employee;
    private RealPaymentCreation realPaymentCreation;

    public ProxyPaymentCreation(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void grantPayment() {
        if (employee.getClass().getName().equals(Coach.class.getName())) {
            realPaymentCreation = new RealPaymentCreation(employee);
            realPaymentCreation.grantPayment();
        } else {
            System.out.println("Clients are not allowed to be payed salary.");
        }
    }
}
