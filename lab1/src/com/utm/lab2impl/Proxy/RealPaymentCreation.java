package com.utm.lab2impl.Proxy;

import com.utm.lab2impl.Adapter.Employee;
import com.utm.lab2impl.Facade.PaymentFacade;

public class RealPaymentCreation implements DistributeSalary {
    PaymentFacade paymentFacade = new PaymentFacade();
    private final Employee employee;

    public RealPaymentCreation(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void grantPayment() {
        System.out.println("Transferring salary: ");
        paymentFacade.pay(employee, 5000);
    }
}
