package com.example.tapnbite.Class;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Food> foodItems; // List of food items in the cart
    private double subtotal; // Subtotal of items
    private double taxRate; // Tax rate (e.g., 0.07 for 7%)

    public Cart(double taxRate) {
        this.foodItems = new ArrayList<>();
        this.taxRate = taxRate;
    }

    public void addFood(Food food) {
        foodItems.add(food);
        calculateSubtotal();
    }

    public void removeFood(Food food) {
        foodItems.remove(food);
        calculateSubtotal();
    }

    private void calculateSubtotal() {
        subtotal = 0;
        for (Food food : foodItems) {
            subtotal += food.getPrice(); // Assuming Food has a getPrice() method
        }
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTax() {
        return subtotal * taxRate;
    }

    public double getTotal() {
        return subtotal + getTax();
    }

    public List<Food> getFoodItems() {
        return foodItems;
    }
}
