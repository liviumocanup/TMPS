package com.utm.lab3impl.Mediator;

import com.utm.lab1impl.Singleton.Coach;
import com.utm.lab2impl.Adapter.Employee;
import com.utm.lab2impl.Composite.Cleaner;
import com.utm.lab2impl.Composite.Manager;

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
