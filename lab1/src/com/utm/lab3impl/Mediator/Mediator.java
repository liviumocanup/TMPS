package com.utm.lab3impl.Mediator;

import com.utm.lab2impl.Adapter.Employee;

public interface Mediator {
    void clean();
    void yelledAt(Employee employee);
    void askForBonus(int amount);
    void registerEmployee(Employee employee);
    void encourage(Employee employee);
    void startWorkingDay();
}
