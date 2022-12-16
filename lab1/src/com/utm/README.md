# Topic: Laboratory Work 3, Behavioral Design Patterns.

### Course: TMPS
### Author: Mocanu Liviu

----

## Overview
In software engineering, behavioral design patterns have the purpose of identifying common communication patterns between different software entities. By doing so, these patterns increase flexibility in carrying out this communication.

&ensp; &ensp; Some examples from this category of design patterns are :

* Chain of Responsibility
* Command
* Interpreter
* Iterator
* Mediator
* Observer
* Strategy
* Memento

## Implementation:

### Iterator
Iterator is a behavioral design pattern that lets you traverse elements of a collection without exposing its underlying representation (list, stack, tree, etc.).

In this example, the Iterator pattern is used to go over the list of Customers a Coach has. I implemented ClientList in a way similar to ArrayList which also accepts generic types and comes with an Iterator and ListIterator:
```java
class ObjectIterator implements Iterator<E> {
        int cursor;

        ObjectIterator() {
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) objectsArray[cursor++];
        }

        @Override
        public void remove() {
            if (cursor - 1 < 0) {
                throw new IllegalStateException();
            }

            ClientList.this.remove(cursor - 1);
            cursor--;
        }
    }
```
In the following piece of code we can see how we use the Iterator and ListIterator, as well some of the differences between them (the snippet is from Main):
```java
        Iterator<Customer> iterator = clientList.iterator();
        System.out.print("Iterator: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getFirstName()+" ");
        }
        System.out.print("\nListIterator: ");
        iterator.remove();
        customer3.setFirstName("customer4");

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
```
Output:
```
===Iterator===
Iterator: customer1 customer2 customer3 
ListIterator: customer1 customer2 ; customer2 customer1 customer4
```
We see that ListIterator has also the possibility of adding/removing new objects as well as iterating backwards.

### State
State is a behavioral design pattern that lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.
The pattern extracts state-related behaviors into separate state classes and forces the original object to delegate the work to an instance of these classes, instead of acting on its own.

In our example the Coach has 3 possible states: Free, High Demand and Vacation and their respective methods meaning a state goes into a certain other. They all extend State:
```java
public abstract class State {
    Coach coach;

    State(Coach coach){
        this.coach = coach;
    }

    public abstract void free();
    public abstract void highDemand(int payOffer);
    public abstract void vacation();
}
```
The feature of the Free State is that it resets the count of customers a Coach has and in case they were on Vacation, the boolean becomes false since they're back to work:
```java
public FreeState(Coach coach) {
        super(coach);
        coach.onVacation = false;
        coach.customers = 0;
    }

    @Override
    public void free() {
        System.out.println("I'm still free, but can take one more customer now.");
        coach.customers++;
    }
    
    //...
```
I won't show all the code here since it's a bit bigger in size, but another feature is that if you call free while the coach is already free, they will pick one more.
If we switch to a HighDemand state, we see that we get approximatively what happens as in the FreeState, but we also get an increase in the *highDemandRate* a Coach has:
```java
    public HighDemandState(Coach coach){
        super(coach);
        coach.onVacation = false;
        coach.customers++;
        coach.highDemandRate = coach.highDemandRate+50;
    }

    @Override
    public void highDemand(int payOffer){
        if(payOffer > coach.highDemandRate){
            System.out.println("Ok, I'll find time for one more customer.");
            coach.customers++;
        }else {
            System.out.println("Right now I can't take any more customers.");
        }
    }

    @Override
    public void free(){
        coach.customers = 0;
        coach.changeState(new FreeState(coach));
        if(coach.highDemandRate == 300){
            coach.askForBonus();
        }
        System.out.println("I'm free now. I'll accept any customer.");
    }
    
    //...
```
As we see, the highDemandRate is to not allow multiple switches from free->highDemand->free and so on over and over again, since that tires the Coach, therefore they would either want a break, or a bonus.
Also, when being in highDemand and being asked to get another customer, they have to declare a payOffer for the trainings with this Coach. If the payOffer isn't good enough, the Coach won't take them as their Client. 
That's to ensure that highDemand is being respected as a state. The higher the Rate the more a Customer will have to offer.


The Vacation State resets the number of customers and the highDemandRate and sets the Coach onVacation for everyone to know they're out of the gym for some time:
```java
    public VacationState(Coach coach) {
        super(coach);
        coach.onVacation = true;
        coach.customers = 0;
        coach.highDemandRate = 100;
    }
```
Here is the output of this snippet from Main:
```java
        System.out.println("\n===State===");
        Coach coach = Coach.getInstance();
        coach.getState().free();
        coach.getState().highDemand(300);
        coach.getState().highDemand(600);
        coach.getState().highDemand(20);
        coach.getState().free();
        coach.getState().vacation();
        coach.getState().vacation();
        coach.getState().highDemand(200);
        coach.getState().vacation();
        coach.getState().free();
```
You'd think the output should repeat because we're calling certain methods multiple times, however, since the state changed, so did the behaviour of the output:
```
===State===
I'm still free, but can take one more customer now.
There are too many customers, I'll have to increase my rate per hour.
Ok, I'll find time for one more customer.
Right now I can't take any more customers.
I'm free now. I'll accept any customer.
Everything looks to be peaceful here, i'm going to take a vacation.
I'm still on vacation. Call me later.
Back to working, a lot of customers were waiting.
I'm going on a vacation now. Too much stress.
Back to working...
```

### Strategy
Strategy is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.
The original object, called context, holds a reference to a strategy object. The context delegates executing the behavior to the linked strategy object. In order to change the way the context performs its work, other objects may replace the currently linked strategy object with another one.

In our case, we have a PayStrategy Interface to enforce the methods the subsequent strategies must have:
```java
public interface PayStrategy{
    void pay(int paymentAmount);
    void collectPaymentDetails();
}
```
We have 2 different strategies. One for a P2P payment and another one for a Bank Invoice, meaning we do a transfer to IBAN.
When called, both of these strategies require different inputs (P2P: name, card Number; BankInvoice: IBAN, IDNP) for both the sender and the receiver.
That happens with a Scanner:
```java
public class P2p implements PayStrategy {
    private String customerName;
    private String customerCardNumber;

    private String coachName;
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
        paymentFacade.payP2P(
                findCustomerByName(this.customerName),
                findCoachByName(this.coachName),
                paymentAmount
        );
    }

    //...
}
```
As we can see we are prompted to introduce the data for our transfer and then when creating the payment, we also introduce the amount as a parameter this time.
For a BankInvoice however, we have less parameters but they are completely different. 
In this case we need the name of the Bank we are using, the receiver's IBAN and IDNP:
```java
public class BankInvoice implements PayStrategy {
    private String bankName;
    private String IBAN;
    private String cardNumber;

    @Override
    public void collectPaymentDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert the name of the bank: ");
        this.bankName = scanner.nextLine();

        System.out.println("Insert your card number: ");
        this.cardNumber = scanner.nextLine();

        System.out.print("Insert the receiver's IBAN: ");
        IBAN = scanner.nextLine();
    }

    @Override
    public void pay(int paymentAmount) {
        System.out.println("Creating payment from: " + cardNumber);
        PaymentFacade paymentFacade = new PaymentFacade();
        paymentFacade.pay(findByIBAN(this.IBAN), paymentAmount, bankName);
    }
    
    //...
}
```
As you can also see, we are also using a different pay method to achieve that and you'll see that better soon in the output created by this snippet from main:
```java
        System.out.println("\n===Strategy===");
        PayStrategy payStrategy = chooseStrategy();
        payStrategy.collectPaymentDetails();
        payStrategy.pay(300);
        
        //...

    private static PayStrategy chooseStrategy(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Choose preferred strategy.
                1 - P2P
                2 - Bank Invoice.""");
        int choice = scanner.nextInt();

        if (choice == 1) {
        return new P2p();
        } else {
        return new BankInvoice();
        }
    }
```
Output for P2P:
```
===Strategy===
Choose preferred strategy.
1 - P2P
2 - Bank Invoice.
1
Insert your name: 
Liviu
Insert your card number: 
5239432714249
Insert the receiver's name: 
Ivan
Insert the receiver's card number: 
7218743294621
Found customer by name: Liviu
Found coach by name: Ivan
Making connection...
All good.
Funds withdrawn from sender.
Bank NY Bank notified.
I got 300.0 more $ for my coaching services this month.
```

Output for BankInvoice:
```
===Strategy===
Choose preferred strategy.
1 - P2P
2 - Bank Invoice.
2
Insert the name of the bank: 
MAIB
Insert your card number: 
5239432714249
Insert the receiver's IBAN: 21484914218742
Creating payment from: 5239432714249
Found coach by IBAN: 21484914218742
Bank MAIB notified.
I got 300.0 more $ for my coaching services this month.
```

### Mediator
Mediator is a behavioral design pattern that lets you reduce chaotic dependencies between objects. The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object.
The Mediator makes it easy to modify, extend and reuse individual components because they’re no longer dependent on the dozens of other classes.

In our case we made a simulation of how a Manager, Coach and Cleaner could interact with each other.
```java
public interface Mediator {
    void clean();
    void yelledAt(Employee employee);
    void askForBonus(int amount);
    void registerEmployee(Employee employee);
    void encourage(Employee employee);
    void startWorkingDay();
}
```
And here is the implementation of this interface.

First we register the Employees and assign them, after which the methods such as clean() or askForBonus() will affect a certain employee.

For example if the Cleaner has been yelled at, when having to clean, the manager will instead encourage them, but no work will be done. Otherwise, the Cleaner will clean the floor and get payed for their service.

YelledAt() and encourage() are polar opposites, one sets the wereYelledAt boolean of the passed employee to true, while the other is setting it to false respectively:
```java
public class ManagerMediator implements Mediator{
    private Coach coach;
    private Manager manager;
    private Cleaner cleaner;

    @Override
    public void registerEmployee(Employee employee) {
        employee.setMediator(this);
        switch (employee.getJobTitle()) {
            case "Cleaner" -> cleaner = (Cleaner) employee;
            case "Manager" -> manager = (Manager) employee;
            case "Coach" -> coach = (Coach) employee;
        }
    }

    @Override
    public void clean() {
        if(!cleaner.yelledAt) {
            System.out.println("Everything is clean. Manager won't yell at anyone.");
            cleaner.getPayed(50);
        }
        else {
            encourage(cleaner);
        }
    }

    @Override
    public void yelledAt(Employee employee) {
        System.out.println("Employee "+employee.getJobTitle()+"'s performance worsened.");
        employee.wereYelledAt(true);
    }

    @Override
    public void encourage(Employee employee) {
        System.out.println("Employee "+employee.getJobTitle()+"'s performance became better.");
        employee.wereYelledAt(false);
    }

    @Override
    public void askForBonus(int amount) {
        System.out.println("Coach asks for bonus.");
        if(!coach.yelledAt) {
            manager.yellAtEmployee(coach);
        }
        else {
            manager.payAll(amount);
            System.out.println("They were granted a "+amount+" bonus.");
        }
    }

    @Override
    public void startWorkingDay(){
        manager.yellAtEmployee(cleaner);
        coach.askForBonus();
        cleaner.clean();
        yelledAt(manager);
        clean();
        askForBonus(150);
    }
}
```
StartWorkingDay() method is actually a snippet of code to show how everything comes together in the Mediator, and this is the only method called in Main to showcase the pattern, besides registering the employees:
```java
        System.out.println("\n===Mediator===");
        ManagerMediator managerMediator = new ManagerMediator();
        managerMediator.registerEmployee(Coach.getInstance());
        managerMediator.registerEmployee(new Manager(Coach.getInstance()));
        managerMediator.registerEmployee(new Cleaner());
        managerMediator.startWorkingDay();
```
Output:
```
===Mediator===
Cleaner yelled at.
Employee Cleaner's performance worsened.
Coach asks for bonus.
Coach yelled at.
Employee Coach's performance worsened.
Employee Cleaner's performance became better.
Employee Manager's performance worsened.
Manager had a breakdown.
Everything is clean. Manager won't yell at anyone.
Coach asks for bonus.
Pay just came in for the department, I should give everyone their share.
I got 150.0 more $ for my coaching services this month.
They were granted a 150 bonus.
```

### Template
Template Method is a behavioral design pattern that defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.

In our example we choose to go again by the Payment realization idea. We have a general PaymentTemplate all Payments should abide by:

```java
public abstract class PaymentTemplate {
    Coach toCoach;

    PaymentTemplate(){}

    boolean verifyCustomerCredentials(String name, String cardNumber) {
        return !name.isBlank() && !cardNumber.isBlank();
    }

    //...

    protected void transferFunds(Employee employee, double amount) {
        notifyBank();
        System.out.println(employee.getPayed(amount));
    }
    
    //...

    public abstract void makePayment(Customer customer, double amount);
}
```
As we see there must be a Coach to which the Customer would transfer the funds and different already implemented methods such as reducing funds or transferring them to a certain Employee.
However, we have an abstract method, that is makePayment that needs to be implemented in order for all of these to come together.

Here we have our P2P payment extending this template:
```java
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

    //...
}
```
Through its constructor we find the name and cardNumber of the receiver of funds, something that is completely different from the other payment we'll showcase shortly after.
By seeing the ToIBAN Payment, we'll also see the differences in the makePayment implemntation:
```java
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
    
    //...
}
```
Here we can see that though the constructor, we are accepting the IBAN and IDNP this time in order to find the Coach.
Another big difference is the fact that this time we create a IBAN Request which has to be processed for a certain amount of time, as well as securing a connection for this transfer.

That happens since usually by creating a transfer to IBAN, a lot bigger sums can be sent and to different countries. That's why we need a request to be processed for that.

And here we have the snippet from Main to showcase this combined:
```java
        Customer payingCustomer = new Customer();
        payingCustomer.setFirstName("Dan");
        payingCustomer.cardNumber = "524924312874";
        PaymentTemplate payment = chooseTemplate();
        payment.makePayment(payingCustomer, 150);

    //...

    private static PaymentTemplate chooseTemplate(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("""
                Choose preferred template.
                1 - P2P
                2 - To IBAN.""");
        int choice=scanner.nextInt();

        if(choice==1){
        return new P2Payment("Ivan","1111111111111111");
        }else{
        return new ToIBAN("Ivan","222222222222222");
        }
    }
```
Output for P2Payment:
```
===Template===
Choose preferred template.
1 - P2P
2 - To IBAN.
1
Funds withdrawn from sender.
Bank notified!
I got 150.0 more $ for my coaching services this month.
P2P transfer completed!
```

Output for ToIBAN:
```
===Template===
Choose preferred template.
1 - P2P
2 - To IBAN.
2
Creating a secure connection for transfer to IBAN.
Making connection...
Success!
The request has been successfully sent. It will be processed within 24hrs.
//...waiting time
Funds withdrawn from sender.
Bank notified!
I got 150.0 more $ for my coaching services this month.
```
## Conclusion

This was a very informative laboratory work where we got to learn about how to properly use and implement a variety of Behavioral Design Patterns, something that must be heavily relied upon in order to write clean and easy to understand code, while also avoiding code smells in order not to have to reformat the written code any further.