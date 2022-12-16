package com.utm.lab3impl.Strategy;

import com.utm.lab1impl.Singleton.Coach;
import com.utm.lab2impl.Adapter.Employee;
import com.utm.lab2impl.Facade.PaymentFacade;

import java.util.Scanner;

public class BankInvoice implements PayStrategy{
    private String bankName;
    private String IBAN;
    private String cardNumber;

    @Override
    public void paymentDetails(String name, String cardNumber) {
        this.bankName = name;
        this.cardNumber = cardNumber;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert IBAN: ");
        IBAN = scanner.nextLine();
    }

    @Override
    public void pay(int paymentAmount, Employee coach) {
        PaymentFacade paymentFacade = new PaymentFacade();
        paymentFacade.pay(coach, paymentAmount);
    }
}
