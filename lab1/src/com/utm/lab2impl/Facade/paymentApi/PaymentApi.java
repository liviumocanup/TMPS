package com.utm.lab2impl.Facade.paymentApi;

import com.utm.lab2impl.Adapter.Employee;

public class PaymentApi {
    BankAccountApi bankAccountApi = new BankAccountApi("NY Bank");

    public void reduceFunds(Employee employee) {
        System.out.println("Funds withdrawn from sender.");
    }

    public void transferFunds(Employee employee, double amount) {
        bankAccountApi.notifyBank();
        System.out.println(employee.getPayed(amount));
    }
}
