package com.utm.lab3impl.Strategy;

import com.utm.lab1impl.PrototypeAndBuilder.Customer;
import com.utm.lab1impl.Singleton.Coach;
import com.utm.lab2impl.Facade.PaymentFacade;

import java.util.Scanner;

public class P2p implements PayStrategy{
    private String customerName;
    private String coachName;
    private String customerCardNumber;
    private String coachCardNumber;

    @Override
    public void collectPaymentDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert your name: ");
        this.customerName = scanner.nextLine();

        System.out.println("Insert your card number: ");
        this.customerCardNumber = scanner.nextLine();

        System.out.println("Insert the receiver's name: ");
        this.coachName = scanner.nextLine();

        System.out.println("Insert the receiver's card number: ");
        this.coachCardNumber = scanner.nextLine();
    }

    @Override
    public void pay(int paymentAmount) {
        PaymentFacade paymentFacade = new PaymentFacade();
        paymentFacade.payP2P(findCustomerByName(
                        this.customerName),
                findCoachByName(this.coachName),
                paymentAmount
        );
    }

    private Customer findCustomerByName(String name) {
        System.out.println("Found customer by name: " + name);
        Customer c = new Customer();
        c.setFirstName(name);
        c.cardNumber = this.customerCardNumber;
        return c;
    }

    private Coach findCoachByName(String name) {
        System.out.println("Found coach by name: " + name);
        Coach c = Coach.getInstance();
        c.firstName = name;
        c.cardNumber = this.coachCardNumber;
        return c;
    }
}
