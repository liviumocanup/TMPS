package com.utm.lab2impl.Facade;

import com.utm.lab1impl.PrototypeAndBuilder.Customer;
import com.utm.lab2impl.Adapter.Employee;
import com.utm.lab2impl.Facade.paymentApi.CustomersApi;
import com.utm.lab2impl.Facade.paymentApi.PaymentApi;

public class PaymentFacade {
    CustomersApi customersApi = new CustomersApi();
    PaymentApi paymentApi = new PaymentApi();

    public void pay(Employee employee, double amount) {
        paymentApi.transferFunds(employee, amount);
    }

    public void pay(Employee employee, double amount, String bankName) {
        paymentApi.transferFunds(employee, amount, bankName);
    }

    public void payP2P(Employee from, Employee to, double amount) {
        customersApi.makingConnection(from, to);
        paymentApi.reduceFunds(from);
        paymentApi.transferFunds(to, amount);
    }

    public void payP2P(Customer from, Employee to, double amount) {
        customersApi.makingConnection(from, to);
        paymentApi.reduceFunds(from);
        paymentApi.transferFunds(to, amount);
    }
}
