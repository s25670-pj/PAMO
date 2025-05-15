package com.example.kalkulatorbmi.model;

public class ShoppingItem {
    private int id;
    private String name;
    private String quantity;
    private boolean isChecked;

    public ShoppingItem(int id, String name, String quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.isChecked = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}