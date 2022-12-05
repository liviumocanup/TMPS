package com.utm.lab1impl.Singleton;

import com.utm.lab1impl.AbstractFactory.Staff;
import com.utm.lab2impl.Adapter.Employee;
import com.utm.lab2impl.Bridge.GymAtendee;

public class Coach extends Staff implements Employee, GymAtendee {
    private static Coach instance = null;

    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public Integer salary;
    public Integer subordinates;

    private Coach() {
        firstName = "Ivan";
        lastName = "Culicov";
        email = "iculicov@mail.ru";
        phoneNumber = "+404404404";
        salary = 1200;
        subordinates = 5;
    }

    public static Coach getInstance() {
        if (instance == null)
            instance = new Coach();
        return instance;
    }

    @Override
    public void getNumberOfSubordinates() {
        System.out.println("I am a Coach, I have " + subordinates + " subordinates.");
    }

    @Override
    public String toString() {
        return "Coach{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", subordinates=" + subordinates +
                '}';
    }

    @Override
    public String getPayed(double amount) {
        return "I got "+amount+" more $ for my coaching services this month.";
    }

    @Override
    public void workout() {
        System.out.println("I have some spare time so I'll work out a bit.");
    }
}
