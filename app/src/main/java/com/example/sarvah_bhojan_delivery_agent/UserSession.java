package com.example.sarvah_bhojan_delivery_agent;

public class UserSession {
    private static UserSession instance;
    private Agent user;

    private UserSession() {
        // Private constructor to prevent instantiation
    }

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUser(Agent user) {
        this.user = user;
    }

    public Agent getUser() {
        return user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void clearSession() {
        user = null;
    }
}
