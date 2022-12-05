package com.utm.lab2impl.Composite;

import com.utm.lab2impl.Adapter.Employee;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Employee {
    private final List<Employee> children = new ArrayList<>();

    public Manager(Employee... employees) {
        add(employees);
    }

    public void add(Employee... employees) {
        children.addAll(List.of(employees));
    }

    public void remove(Employee... employees) {
        children.removeAll(List.of(employees));
    }

    public void payAll(double amount) {
        System.out.println("Pay just came in for the department, I should give everyone their share.");
        if (children.size() != 0) {
            children.forEach(employee -> System.out.println(employee.getPayed(amount / children.size())));
        } else System.out.println("But right now I don't have any underdogs.");
    }

    @Override
    public String getPayed(double amount) {
        return "I got payed " + amount + " for the month.";
    }
}