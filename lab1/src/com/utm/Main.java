package com.utm;

import com.utm.AbstractFactory.AbstractFactory;
import com.utm.AbstractFactory.FactoryCreator;
import com.utm.Factory.ClientFactory;
import com.utm.Factory.IClient;
import com.utm.PrototypeAndBuilder.Customer;
import com.utm.PrototypeAndBuilder.UserRepo;
import com.utm.Singleton.Coach;

public class Main {

    public static <Client> void main(String[] args) {
        //Singleton
        System.out.println("\n---Singleton---");
        Coach coach = Coach.getInstance();
        Coach coach1 = Coach.getInstance();
        System.out.println(coach);
        System.out.println(coach1);
        System.out.println("Are they equal? :" + coach.equals(coach1));

        //Prototype
        System.out.println("\n---Prototype---");
        Customer user1 = (Customer) UserRepo.getUser("Alan Waker");
        System.out.println(user1);
        System.out.println(UserRepo.getUser("Alan Waker"));
        System.out.println("Are they equal? :" + user1.equals(UserRepo.getUser("Alan Waker")));
        System.out.println(UserRepo.getUser("George Buch"));

        //Factory
        System.out.println("\n---Factory---");
        ClientFactory clientFactory = new ClientFactory();
        IClient client = clientFactory.createClient("Customer");
        client.payMonthlyFee();

        IClient client2 = clientFactory.createClient("Premium");
        client2.payMonthlyFee();

        //AbstractFactory
        System.out.println("\n---Abstract Factory---");
        AbstractFactory staffFactory = FactoryCreator.getFactory("Staff");
        staffFactory.getStaff("Coach").getNumberOfSubordinates();
        staffFactory.getStaff("GymCoordinator").getNumberOfSubordinates();

        AbstractFactory clientFactory2 = FactoryCreator.getFactory("Client");
        clientFactory2.getClient("Customer").payMonthlyFee();
        clientFactory2.getClient("Premium").payMonthlyFee();

    }
}
