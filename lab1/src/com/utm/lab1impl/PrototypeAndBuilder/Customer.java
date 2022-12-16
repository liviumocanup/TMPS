package com.utm.lab1impl.PrototypeAndBuilder;

import com.utm.lab1impl.Factory.IClient;
import com.utm.lab2impl.Bridge.GymAtendee;

public class Customer extends User implements IClient, GymAtendee {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String phoneNumber;
    private String address;
    private Double weight;
    private Double height;

    public void setFirstName(String name){
        firstName = name;
    }

    public Customer() {
    }


    private Customer(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.age = builder.age;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.height = builder.height;
        this.weight = builder.weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }

    @Override
    public void payMonthlyFee() {
        System.out.println("I'm a regular customer who has 100$ due.");
    }

    @Override
    public void workout() {
        System.out.println("I'm paying money for this so I better exercise hard!");
    }

    public static class UserBuilder {
        private final String firstName;
        private final String lastName;
        private String email;
        private int age;
        private String phoneNumber;
        private String address;
        private Double weight;
        private Double height;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phoneNumber(String phone) {
            this.phoneNumber = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder weight(Double weight) {
            this.weight = weight;
            return this;
        }

        public UserBuilder height(Double height) {
            this.height = height;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
