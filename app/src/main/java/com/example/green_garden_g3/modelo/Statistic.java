package com.example.green_garden_g3.modelo;

import java.util.ArrayList;

public class Statistic {
    private final ArrayList<StatisticConsumption> statisticConsumptions;
    private final ArrayList<MaximumConsumption> maximumConsumptions;

    public Statistic(ArrayList<StatisticConsumption> statisticConsumptions, ArrayList<MaximumConsumption> maximumConsumptions) {
        this.statisticConsumptions = statisticConsumptions;
        this.maximumConsumptions = maximumConsumptions;
    }

    public ArrayList<StatisticConsumption> getStatisticConsumptions() {
        return statisticConsumptions;
    }

    public ArrayList<MaximumConsumption> getMaximumConsumptions() {
        return maximumConsumptions;
    }

    public double getTotalCost() {
        double total = 0;

        for (StatisticConsumption c : statisticConsumptions) {
            total += c.getTotal();
        }

        return total;
    }
}
