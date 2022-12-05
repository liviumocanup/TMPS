package com.utm.lab2impl.Adapter;

public class CreditAdapter implements CreditCardPossessor {
    private final Employee employee;
    private Double total = 0d;

    public CreditAdapter(Employee employee) {
        this.employee = employee;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String addFunds(double amount) {
        setTotal(getTotal() + amount);
        return employee.getPayed(amount) + ". These funds were transferred to a total of: " + getTotal();
    }
}
