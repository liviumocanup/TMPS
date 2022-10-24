package com.utm.Singleton;

import com.utm.AbstractFactory.Staff;

public class Coach extends Staff {
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
}
