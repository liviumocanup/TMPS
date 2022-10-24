package com.utm.PrototypeAndBuilder;

abstract class User implements Cloneable {
    protected String firstName = "Unknown";
    protected String lastName = "Unknown";
    protected String email = "Unknown";
    protected String phoneNumber = "Unknown";
    protected Double weight;
    protected Double height;

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public abstract String toString();
}
