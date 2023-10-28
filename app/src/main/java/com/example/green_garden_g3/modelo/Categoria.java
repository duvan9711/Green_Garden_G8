package com.example.green_garden_g3.modelo;

public abstract class Categoria {
    // Atributos
    String nombre;
    int costo;
    String unidad;

    // Constructor
    public Categoria(String nombre, int costo, String unidad) {
        this.nombre = nombre;
        this.costo = costo;
        this.unidad = unidad;
    }

    // Métodos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public abstract double costoConsumo(double cantidad);
}
