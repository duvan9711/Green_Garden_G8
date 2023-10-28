package com.example.green_garden_g3.modelo;

public class Abono extends Categoria {
    public Abono(String nombre, int costo, String unidad) {
        super(nombre, costo, unidad);
    }

    @Override
    public double costoConsumo(double cantidad) {
        System.out.println("Calculando consumo de abono");
        return costo * cantidad;
    }
}
