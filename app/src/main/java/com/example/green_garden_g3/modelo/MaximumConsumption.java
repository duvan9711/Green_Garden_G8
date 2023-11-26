package com.example.green_garden_g3.modelo;

public class MaximumConsumption {
    private int year;
    private String month;
    private Category category;
    private double quantity;

    public MaximumConsumption(int year, String month, Category category, double quantity) {
        this.year = year;
        this.month = month;
        this.category = category;
        this.quantity = quantity;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
