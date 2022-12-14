package com.utm.lab1impl.AbstractFactory;

import com.utm.lab1impl.Factory.IClient;
import com.utm.lab1impl.Singleton.Coach;

import java.util.Locale;

public class StaffFactory extends AbstractFactory {
    @Override
    public Staff getStaff(String staff) {
        if (staff == null || staff.isEmpty())
            return null;
        switch (staff.toUpperCase(Locale.ROOT)) {
            case "COACH":
                return Coach.getInstance();
            case "GYMCOORDINATOR":
                return new GymCoordinator();
            default:
                throw new IllegalArgumentException("Unknown channel " + staff);
        }
    }

    @Override
    public IClient getClient(String client) {
        return null;
    }
}
