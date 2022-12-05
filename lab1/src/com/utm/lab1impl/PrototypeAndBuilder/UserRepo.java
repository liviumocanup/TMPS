package com.utm.lab1impl.PrototypeAndBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserRepo {
    private static final Map<String, User> userMap = new HashMap<>();

    static {
        userMap.put("Alan Waker", new Customer.UserBuilder("Alan", "Waker")
                .email("awaker@gmail.com")
                .phoneNumber("+373889900")
                .weight(67d)
                .height(173d)
                .build());
        userMap.put("George Buch", new PremiumCustomer("George", "Buch", "gbuch@gmail.com", "+373009988", 78d, 185d, 15d, LocalDateTime.now()));
    }

    public static User getUser(String userFullName) {
        return (User) userMap.get(userFullName).clone();
    }
}
