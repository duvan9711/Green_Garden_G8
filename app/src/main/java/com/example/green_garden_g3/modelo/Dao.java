package com.example.green_garden_g3.modelo;

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

    public ArrayList<Consumption> getConsumptions(File file, String userId) {
        ArrayList<Consumption> list = new ArrayList<>();

        try {
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

    public ArrayList<Statistic> getStatistics(File file, String userId) {
        ArrayList<Statistic> statistics = new ArrayList<>();
        ArrayList<Consumption> consumptions = getConsumptions(file, userId);
        HashMap<String, Category> categoryHashMap = getCategory();
        DateFormat format = new SimpleDateFormat("yyyy-MMM");

        consumptions.sort((a, b)-> a.getCategory().compareTo(b.getCategory()) +
                a.getConsumptionDate().compareTo(b.getConsumptionDate()));

        for (Consumption c : consumptions) {
            String period = format.format(c.getConsumptionDate());
            int year = Integer.parseInt(period.split("-")[0]);
            String month = period.split("-")[1];
            int i = findDataIndex(statistics, year, month, categoryHashMap.get(c.getCategory()));

            if (i >= 0) {
                double quantity = statistics.get(i).getQuantity() + c.getQuantity();
                statistics.get(i).setQuantity(quantity);
            } else {
                statistics.add(
                        new Statistic(
                                year,
                                month,
                                categoryHashMap.get(c.getCategory()),
                                c.getQuantity(),
                                c.getCost()
                        )
                );
            }
        }

        return statistics;
    }

    private int findDataIndex(ArrayList<Statistic> statistics, int year, String month, Category category) {
        int index = -1;
        int size = statistics.size();

        for (int i = 0; i < size; i++) {
            Statistic statistic = statistics.get(i);
            if (statistic.getYear() == year &&
                    statistic.getMonth().equals(month) &&
                    statistic.getCategory().equals(category)) {
                index = i;
                break;
            }
        }

        return index;
    }

    public void saveConsumption(File file, Consumption consume) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(consume.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
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
