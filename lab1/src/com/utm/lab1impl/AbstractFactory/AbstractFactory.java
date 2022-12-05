package com.utm.lab1impl.AbstractFactory;

import com.utm.lab1impl.Factory.IClient;

public abstract class AbstractFactory {
    public abstract Staff getStaff(String staff);

    public abstract IClient getClient(String client);
}
