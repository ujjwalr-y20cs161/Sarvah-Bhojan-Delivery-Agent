package com.example.sarvah_bhojan_delivery_agent;

import android.location.Location;

public class Order {
    private String id,customerName,status,pickUp,dropDown,customerId,AgentId;
    private Location pickAddressLocate,dropAddressLocate;
    private int Fare,Distance;

    public Order(String id, String customerName,String CustomerId,String pickUp,String dropDown, Location pickAddress, Location dropAddress, int fare, int distance) {
        this.id = id;
        this.customerName = customerName;
        this.customerId = CustomerId;
        this.status = "Order Created!";
        this.dropDown = dropDown;
        this.pickUp = pickUp;
        this.pickAddressLocate = pickAddress;
        this.dropAddressLocate = dropAddress;
        this.Fare = fare;
        this.Distance = distance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAgentId() {
        return AgentId;
    }

    public void setAgentId(String agentId) {
        AgentId = agentId;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Location getPickAddress() {
        return pickAddressLocate;
    }

    public Location getDropAddress() {
        return dropAddressLocate;
    }

    public int getFare() {
        return Fare;
    }

    public int getDistance() {
        return Distance;
    }

    public String getPickUp() {
        return pickUp;
    }

    public String getDropDown() {
        return dropDown;
    }
}
