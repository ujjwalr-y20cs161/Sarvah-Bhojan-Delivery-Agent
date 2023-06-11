package com.example.sarvah_bhojan_delivery_agent;


public class OrderSession {
    private static OrderSession instance;
    private Order order;

    private OrderSession() {
        // Private constructor to prevent instantiation
    }

    public static synchronized OrderSession getInstance() {
        if (instance == null) {
            instance = new OrderSession();
        }
        return instance;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isOrderAccepted() {
        return order != null;
    }

    public void clearSession() {
        order = null;
    }
}

