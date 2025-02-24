package com.example.tapnbite.Class;

import java.util.List;

public class Store {
    private String storeId; // Unique identifier for the restaurant
    private String canteen;
    private String storeName;
    private List<Food> menu; // List of food items offered by the restaurant

    public Store(String storeID, String canteen, String name, List<Food> menu) {
        this.storeId = storeID;
        this.canteen = canteen;
        this.storeName = name;
        this.menu = menu;
    }

    public String getStoreID() {
        return storeId;
    }

    public String getCanteen() {
        return canteen;
    }

    public String getName() {
        return storeName;
    }

    public List<Food> getMenu() {
        return menu;
    }
}
