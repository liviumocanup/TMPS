package com.utm.lab2impl.Composite;

import com.utm.lab2impl.Adapter.Employee;

public class Cleaner implements Employee {
    @Override
    public String getPayed(double amount) {
        return "Only "+ amount+"??? Where's the rest?";
    }
}
