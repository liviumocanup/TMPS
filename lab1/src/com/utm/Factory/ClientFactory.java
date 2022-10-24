package com.utm.Factory;

import com.utm.AbstractFactory.AbstractFactory;
import com.utm.AbstractFactory.Staff;
import com.utm.PrototypeAndBuilder.Customer;
import com.utm.PrototypeAndBuilder.PremiumCustomer;

import java.util.Locale;

public class ClientFactory extends AbstractFactory {
    public IClient createClient(String argument) {
        if (argument == null || argument.isEmpty())
            return null;
        switch (argument.toUpperCase(Locale.ROOT)) {
            case "CUSTOMER":
                return new Customer();
            case "PREMIUM":
                return new PremiumCustomer();
            default:
                throw new IllegalArgumentException("Unknown channel " + argument);
        }
    }

    @Override
    public Staff getStaff(String staff) {
        return null;
    }

    @Override
    public IClient getClient(String client) {
        return createClient(client);
    }
}
