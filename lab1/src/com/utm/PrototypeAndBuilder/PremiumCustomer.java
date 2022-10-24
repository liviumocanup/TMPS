package com.utm.PrototypeAndBuilder;

import com.utm.Factory.IClient;

import java.time.LocalDateTime;

public class PremiumCustomer extends User implements IClient {
    public Double weightLost;
    public LocalDateTime timeAccessed;

    public PremiumCustomer(String firstName, String lastName, String email, String phoneNumber, Double weight, Double height, Double weightLost, LocalDateTime timeAccessed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.weight = weight;
        this.height = height;
        this.weightLost = weightLost;
        this.timeAccessed = timeAccessed;
    }

    public PremiumCustomer() {
    }

    @Override
    public String toString() {
        return "PremiumCustomer{" +
                "weightLost=" + weightLost +
                ", timeAccessed=" + timeAccessed +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }

    @Override
    public void payMonthlyFee() {
        System.out.println("I'm a Premium Customer that has 85$ due, but that can wait until next month to pay.");
    }
}
