package com.utm;

import com.utm.lab1impl.AbstractFactory.AbstractFactory;
import com.utm.lab1impl.AbstractFactory.FactoryCreator;
import com.utm.lab1impl.Factory.ClientFactory;
import com.utm.lab1impl.Factory.IClient;
import com.utm.lab1impl.PrototypeAndBuilder.Customer;
import com.utm.lab1impl.PrototypeAndBuilder.UserRepo;
import com.utm.lab1impl.Singleton.Coach;
import com.utm.lab2impl.Adapter.CreditAdapter;
import com.utm.lab2impl.Adapter.CreditCardPossessor;
import com.utm.lab2impl.Adapter.Employee;
import com.utm.lab2impl.Bridge.Receptionist;
import com.utm.lab2impl.Composite.Cleaner;
import com.utm.lab2impl.Composite.Manager;
import com.utm.lab2impl.Facade.PaymentFacade;
import com.utm.lab2impl.Proxy.DistributeSalary;
import com.utm.lab2impl.Proxy.ProxyPaymentCreation;

public class Main {

    public static void main(String[] args) {
        //lab1();

        lab2();


    }

    public static void lab1(){
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

    public static void lab2(){
        System.out.println("===Adapter===");
        Employee coach = Coach.getInstance();
        CreditCardPossessor cardAdapter = new CreditAdapter(coach);
        System.out.println(cardAdapter.addFunds(30));

        System.out.println("\n===Bridge===");
        Receptionist receptionist = new Receptionist(Coach.getInstance());
        receptionist.checkIn();
        System.out.println();
        receptionist = new Receptionist(new Customer());
        receptionist.checkIn();

        System.out.println("\n===Composite===");
        Employee manager1 = new Manager();
        Manager manager2 = new Manager(coach);
        manager2.add(new Cleaner(), manager1);
        manager2.payAll(1500);

        System.out.println("\n===Facade===");
        PaymentFacade paymentFacade = new PaymentFacade();
        paymentFacade.pay(manager2, 200);
        System.out.println();
        paymentFacade.payP2P(manager1, coach, 200);

        System.out.println("\n===Proxy===");
        DistributeSalary distributeSalary = new ProxyPaymentCreation(coach);
        distributeSalary.grantPayment();
    }
}
