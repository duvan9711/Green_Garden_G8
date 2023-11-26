package com.example.green_garden_g3.modelo;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Dao {
    // Métodos
    public HashMap<String, Category> getCategory() {
        HashMap<String, Category> map = new HashMap<>();

        map.put("Agua", new Water(1, "Agua", 100, "lt"));
        map.put("Abono", new Fertilizer(2, "Abono", 150, "gr"));
        map.put("Energía", new Energy(3, "Energía", 817.8, "kWh"));

        return map;
    }

    public ArrayList<Consumption> getConsumptions(File filesDir, String consumptionCategory, String userId) {
        ArrayList<Consumption> list = new ArrayList<>();

        try {
            String filename = getFilename(consumptionCategory);
            File file = new File(filesDir, filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String item;

            while ((item = bufferedReader.readLine()) != null) {
                String[] data = item.split(",");

                long id = Long.parseLong(data[0]);
                Date consumptionDate = convertToDate(data[1]);
                String plant = data[2];
                String category = data[3];
                double quantity = Double.parseDouble(data[4]);
                double cost = Double.parseDouble(data[5]);
                String userIde = data[6];

                if (userId.equals(userIde)) {
                    Consumption consumption = new Consumption(
                            id, consumptionDate, plant, category, quantity, cost, userIde
                    );
                    list.add(consumption);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public HashMap<String, Plant> getPlants() {
        HashMap<String, Plant> map = new HashMap<>();

        map.put("Diente de león", new Plant("Diente de león", "Herbáceas"));
        map.put("Lechuga", new Plant("Lechuga", "Hortalizas"));
        map.put("Manzanilla", new Plant("Manzanilla", "Herbáceas"));
        map.put("Papa", new Plant("Papa", "Tubérculos"));
        map.put("Perejil", new Plant("Perejil", "Verduras"));
        map.put("Repollo", new Plant("Repollo", "Verduras"));
        map.put("Tomate", new Plant("Tomate", "Hortalizas"));
        map.put("Zanahoria", new Plant("Zanahoria", "Tubérculos"));

        return map;
    }

    public Statistic getStatistics(File filesDir, String userId) {
        HashMap<String, Category> categoryHashMap = getCategory();
        Statistic statistics;
        ArrayList<StatisticConsumption> statisticConsumptions = new ArrayList<>();
        ArrayList<MaximumConsumption> maximumConsumptions = new ArrayList<>();

        String category = "Abono";
        setStatistics(filesDir, userId, category, categoryHashMap, statisticConsumptions, maximumConsumptions);

        category = "Agua";
        setStatistics(filesDir, userId, category, categoryHashMap, statisticConsumptions, maximumConsumptions);

        category = "Energía";
        setStatistics(filesDir, userId, category, categoryHashMap, statisticConsumptions, maximumConsumptions);

        statistics = new Statistic(statisticConsumptions, maximumConsumptions);

        return statistics;
    }

    private void setStatistics(File filesDir, String userId, String category, HashMap<String, Category> categoryHashMap, ArrayList<StatisticConsumption> statisticConsumptions, ArrayList<MaximumConsumption> maximumConsumptions) {
        ArrayList<Consumption> consumptions = getConsumptions(filesDir, category, userId);

        consumptions.sort((a, b) -> a.getConsumptionDate().compareTo(b.getConsumptionDate()));

        ArrayList<StatisticConsumption> staConsumptions = getStatisticConsumption(categoryHashMap, consumptions);
        MaximumConsumption maxConsumption = getMaximumConsumption(categoryHashMap, staConsumptions);

        statisticConsumptions.addAll(staConsumptions);
        if (maxConsumption != null) {
            maximumConsumptions.add(maxConsumption);
        }
    }

    private ArrayList<StatisticConsumption> getStatisticConsumption(HashMap<String, Category> categoryHashMap, ArrayList<Consumption> consumptions) {
        ArrayList<StatisticConsumption> statisticConsumptions = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MMM");

        for (Consumption c : consumptions) {
            String period = format.format(c.getConsumptionDate());
            int year = Integer.parseInt(period.split("-")[0]);
            String month = period.split("-")[1];
            int i = findDataIndex(statisticConsumptions, year, month, categoryHashMap.get(c.getCategory()));

            if (i >= 0) {
                double quantity = statisticConsumptions.get(i).getQuantity() + c.getQuantity();
                statisticConsumptions.get(i).setQuantity(quantity);
            } else {
                statisticConsumptions.add(
                        new StatisticConsumption(
                                year,
                                month,
                                categoryHashMap.get(c.getCategory()),
                                c.getQuantity(),
                                c.getCost()
                        )
                );
            }
        }

        return statisticConsumptions;
    }

    private MaximumConsumption getMaximumConsumption(HashMap<String, Category> categoryHashMap, ArrayList<StatisticConsumption> statisticConsumptions) {
        if (statisticConsumptions.size() > 0) {
            int year = 1970;
            String month = "Jun";
            Category category = categoryHashMap.get("");
            double quantity = 0;

            for (StatisticConsumption s : statisticConsumptions) {
                if (s.getQuantity() > quantity) {
                    year = s.getYear();
                    month = s.getMonth();
                    category = s.getCategory();
                    quantity = s.getQuantity();
                }
            }

            return new MaximumConsumption(year, month, category, quantity);
        }

        return null;
    }

    private int findDataIndex(ArrayList<StatisticConsumption> statistics, int year, String month, Category category) {
        int index = -1;
        int size = statistics.size();

        for (int i = 0; i < size; i++) {
            StatisticConsumption statistic = statistics.get(i);
            if (statistic.getYear() == year &&
                    statistic.getMonth().equals(month) &&
                    statistic.getCategory().equals(category)) {
                index = i;
                break;
            }
        }

        return index;
    }

    public void saveConsumption(File filesDir, Consumption consume) {
        try {
            String filename = getFilename(consume.getCategory());
            File file = new File(filesDir, filename);
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(consume.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    @NonNull
    private static String getFilename(String category) {
        String filename;

        switch (category) {
            case "Abono":
                filename = "fertilizer.txt";
                break;
            case "Agua":
                filename = "water.txt";
                break;
            case "Energía":
                filename = "energy.txt";
                break;
            default:
                filename = "other";
                break;
        }
        return filename;
    }

    public Date convertToDate(String value) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(value);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
