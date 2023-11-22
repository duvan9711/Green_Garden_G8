package com.example.green_garden_g3.modelo;

public class Energy extends Category {
    public Energy(int id, String name, double cost, String unit) {
        super(id, name, cost, unit);
    }

    @Override
    public double consumptionCost(double quantity) {
        System.out.println("Calculando consumo energía");
        return cost * quantity;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + cost + "," + unit;
    }
}
