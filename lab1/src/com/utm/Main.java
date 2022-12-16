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
import com.utm.lab3impl.Iterator.ClientList;
import com.utm.lab3impl.Mediator.ManagerMediator;
import com.utm.lab3impl.Strategy.BankInvoice;
import com.utm.lab3impl.Strategy.P2p;
import com.utm.lab3impl.Strategy.PayStrategy;

import java.util.Iterator;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {
        //lab1();

        //lab2();

        lab3();
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

    public static void lab3(){
        System.out.println("===Iterator===");
        ClientList<Customer> clientList = new ClientList<>();
        Customer customer1 = new Customer();
        customer1.setFirstName("1");
        Customer customer2 = new Customer();
        customer2.setFirstName("2");
        Customer customer3 = new Customer();
        customer3.setFirstName("3");
        clientList.add(customer1); clientList.add(customer2); clientList.add(customer3);

        Iterator<Customer> iterator = clientList.iterator();
        System.out.print("Iterator: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getFirstName()+" ");
        }
        System.out.print("\nListIterator: ");
        iterator.remove();
        customer3.setFirstName("4");

        ListIterator<Customer> listIterator = clientList.listIterator();
        listIterator.add(customer3);
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next().getFirstName()+" ");
        }
        System.out.print("; ");
        listIterator = clientList.listIterator(listIterator.previousIndex());
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous().getFirstName()+" ");
        }
        System.out.println(listIterator.next().getFirstName()+" ");

        System.out.println("\n===Mediator===");
        ManagerMediator managerMediator = new ManagerMediator();
        managerMediator.registerEmployee(Coach.getInstance());
        managerMediator.registerEmployee(new Manager(Coach.getInstance()));
        managerMediator.registerEmployee(new Cleaner());
        managerMediator.startWorkingDay();

        System.out.println("\n===Strategy===");
        Customer customer = new Customer();
        PayStrategy payStrategy;
        String whichStrategy = "P2P";
        if(whichStrategy.equals("P2P"))
            payStrategy = new P2p();
        else payStrategy = new BankInvoice();
        payStrategy.paymentDetails("Ivan Postolachi", "393929393939");
        payStrategy.pay(300, (Employee) customer);

        whichStrategy = "BankInvoice";
        if(whichStrategy.equals("P2P"))
            payStrategy = new P2p();
        else payStrategy = new BankInvoice();
        payStrategy.paymentDetails("MAIB", "121212121212");
        payStrategy.pay(300, Coach.getInstance());

//
//        System.out.println("\n===Facade===");
//        PaymentFacade paymentFacade = new PaymentFacade();
//        paymentFacade.pay(manager2, 200);
//        System.out.println();
//        paymentFacade.payP2P(manager1, coach, 200);
//
//        System.out.println("\n===Proxy===");
//        DistributeSalary distributeSalary = new ProxyPaymentCreation(coach);
//        distributeSalary.grantPayment();
    }
}
