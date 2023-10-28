package com.example.green_garden_g3.modelo;

public class Agua extends Categoria {
    // Constructor
    public Agua(String nombre, int costo, String unidad) {
        super(nombre, costo, unidad);
    }

    // Métodos
    @Override
    public double costoConsumo(double cantidad) {
        System.out.println("Calculando consumo de agua");
        return costo * cantidad;
    }
}
