package com.utm.lab3impl.Strategy;

import com.utm.lab1impl.Singleton.Coach;
import com.utm.lab2impl.Facade.PaymentFacade;

import java.util.Scanner;

public class BankInvoice implements PayStrategy{
    private String bankName;
    private String IBAN;
    private String cardNumber;

    @Override
    public void collectPaymentDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert the name of the bank: ");
        this.bankName = scanner.nextLine();

        System.out.println("Insert your card number: ");
        this.cardNumber = scanner.nextLine();

        System.out.print("Insert the receiver's IBAN: ");
        IBAN = scanner.nextLine();
    }

    @Override
    public void pay(int paymentAmount) {
        System.out.println("Creating payment from: "+cardNumber);
        PaymentFacade paymentFacade = new PaymentFacade();
        paymentFacade.pay(findByIBAN(this.IBAN), paymentAmount, bankName);
    }

    private Coach findByIBAN(String IBAN){
        System.out.println("Found coach by IBAN: "+ IBAN);
        Coach c = Coach.getInstance();
        c.IBAN = IBAN;
        return c;
    }
}
