package com.example.green_garden_g3.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consumption {
    //Atributos
    private long id;
    private Date consumptionDate;
    private String plant;
    private String category;
    private double quantity;
    private double cost;
    private final String idUser;

    public Consumption(long id, Date consumptionDate, String plant, String category, double quantity, double cost, String idUser) {
        this.id = id;
        this.consumptionDate = consumptionDate;
        this.plant = plant;
        this.category = category;
        this.quantity = quantity;
        this.cost = cost;
        this.idUser = idUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getIdUser() {
        return idUser;
    }

    @Override
    public String toString() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(consumptionDate);

        return id + ","
                + date  + ","
                + plant + ","
                + category + ","
                + quantity+ ","
                + cost + ","
                + idUser;
    }
}
