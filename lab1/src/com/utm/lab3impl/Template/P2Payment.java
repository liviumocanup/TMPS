package com.utm.lab3impl.Template;

import com.utm.lab1impl.PrototypeAndBuilder.Customer;
import com.utm.lab1impl.Singleton.Coach;

public class P2Payment extends PaymentTemplate {
    public P2Payment(String toName, String toCardNumber) {
        this.toCoach = getReceiverByNameAndCardNumber(toName, toCardNumber);
    }

    @Override
    public void makePayment(Customer customer, double amount) {
        if(verifyCustomerCredentials(customer.getFirstName(), customer.cardNumber)){
            reduceFunds(customer);
            transferFunds(toCoach, amount);
            System.out.println("P2P transfer completed!");
        }else {
            System.out.println("Please verify your credentials. Transfer cancelled.");
        }
    }

    Coach getReceiverByNameAndCardNumber(String name, String cardNumber) {
        Coach c = new Coach();
        c.firstName = name;
        c.cardNumber = cardNumber;
        return c;
    }
}
