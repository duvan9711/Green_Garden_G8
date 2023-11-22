package com.example.green_garden_g3.modelo;

public abstract class Category {
    // Atributos
    int id;
    String name;
    double cost;
    String unit;

    // Constructor
    public Category(int id, String name, double cost, String unit) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    // Métodos
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public abstract double consumptionCost(double quantity);

    public abstract String toString();
}
