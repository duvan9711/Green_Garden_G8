package com.example.green_garden_g3.modelo;

import java.util.Date;

public class Registro {
    //Atributos
    Date fechaConsumo;
    String planta;
    String categoria;
    double cantidad;

    public Registro(Date fechaConsumo, String planta, String categoria, double cantidad) {
        this.fechaConsumo = fechaConsumo;
        this.planta = planta;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
