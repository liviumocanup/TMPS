package com.utm.AbstractFactory;

import com.utm.Factory.ClientFactory;

public class FactoryCreator {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("Client")) {
            return new ClientFactory();
        } else if (choice.equalsIgnoreCase("Staff")) {
            return new StaffFactory();
        }
        return null;
    }

}
