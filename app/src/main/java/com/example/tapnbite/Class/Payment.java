package com.example.tapnbite.Class;

public class Payment {
    private String paymentId; // Unique identifier for the payment
    private double totalAmount; // Amount to be paid
    private String paymentStatus; // e.g., "paid", "pending", "failed"

    // Constructor, getters, and setters
    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void processPayment() {
        // Logic to process the payment
        // This could involve calling an API or integrating with a payment gateway
        System.out.println("Processing payment of $" + totalAmount);
    }
}
