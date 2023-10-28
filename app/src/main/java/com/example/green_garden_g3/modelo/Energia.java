package com.example.green_garden_g3.modelo;

public class Energia extends Categoria{
    public Energia(String nombre, int costo, String unidad) {
        super(nombre, costo, unidad);
    }

    @Override
    public double costoConsumo(double cantidad) {
        System.out.println("Calculando consumo energía");
        return costo * cantidad;
    }
}
