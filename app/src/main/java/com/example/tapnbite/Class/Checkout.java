package com.example.tapnbite.Class;

public class Checkout {
    private Cart cart;

    public Checkout(Cart cart) {
        this.cart = cart;
    }

    public void displayPaymentOverview() {
        System.out.println("Payment Overview:");
        System.out.println("Total Items: " + cart.getFoodItems().size());
        System.out.println("Subtotal: $" + String.format("%.2f", cart.getSubtotal()));
        System.out.println("Tax: $" + String.format("%.2f", cart.getTax()));
        System.out.println("Total: $" + String.format("%.2f", cart.getTotal()));
    }

    public void proceedToPayment() {
        // Logic to proceed to payment
        // This could involve navigating to a payment screen or processing payment
        System.out.println("Proceeding to payment...");
    }
}
