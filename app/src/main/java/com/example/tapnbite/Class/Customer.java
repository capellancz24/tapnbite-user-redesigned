package com.example.tapnbite.Class;

public class Customer {
    private String userID;
    private String customerID;
    private String customerNUID;
    private String customerName;
    private String customerEmail;
    private String customerBalance;

    public Customer(String userID, String customerID, String customerNUID, String customerName, String customerEmail, String customerBalance) {
        this.userID = userID;
        this.customerID = customerID;
        this.customerNUID = customerNUID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerBalance = customerBalance;
    }

    public String getUserID() {
        return userID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerNUID() {
        return customerNUID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerBalance() {
        return customerBalance;
    }
}
