package com.utm.AbstractFactory;

import com.utm.Factory.IClient;

public abstract class AbstractFactory {
    public abstract Staff getStaff(String staff);

    public abstract IClient getClient(String client);
}
