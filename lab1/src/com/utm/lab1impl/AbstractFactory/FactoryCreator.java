package com.utm.lab1impl.AbstractFactory;

import com.utm.lab1impl.Factory.ClientFactory;

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
