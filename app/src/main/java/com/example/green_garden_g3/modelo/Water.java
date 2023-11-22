package com.example.green_garden_g3.modelo;

public class Water extends Category {
    // Constructor
    public Water(int id, String name, double cost, String unit) {
        super(id, name, cost, unit);
    }

    // Métodos
    @Override
    public double consumptionCost(double quantity) {
        System.out.println("Calculando consumo de agua");
        return cost * quantity;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + cost + "," + unit;
    }
}
