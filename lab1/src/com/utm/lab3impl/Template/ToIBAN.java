package com.utm.lab3impl.Template;

import com.utm.lab1impl.PrototypeAndBuilder.Customer;
import com.utm.lab1impl.Singleton.Coach;

public class ToIBAN extends PaymentTemplate {
    public ToIBAN(String toIBAN, String toIDNP) {
        this.toCoach = getReceiverByIBANAndIDNP(toIBAN, toIDNP);
    }

    @Override
    public void makePayment(Customer customer, double amount) {
        if (verifyCustomerCredentials(customer.getFirstName(), customer.cardNumber)) {
            System.out.println("Creating a secure connection for transfer to IBAN.");
            makingConnection(customer, toCoach);
            createIBANRequest(customer, toCoach, amount);
        } else {
            System.out.println("Please verify your credentials. Transfer cancelled.");
        }
    }

    void createIBANRequest(Customer customer, Coach coach, double amount) {
        System.out.println("The request has been successfully sent. It will be processed within 24hrs.");
        proccessRequest(2000);
        reduceFunds(customer);
        transferFunds(coach, amount);
    }

    public static void proccessRequest(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    Coach getReceiverByIBANAndIDNP(String IBAN, String IDNP) {
        Coach c = new Coach();
        c.IBAN = IBAN;
        c.IDNP = IDNP;
        return c;
    }
}
