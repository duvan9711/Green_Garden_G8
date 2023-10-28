package com.example.green_garden_g3.modelo;

import java.util.HashMap;

public class Dao {
    // Métodos
    public HashMap<String, Planta> getPlantas() {
        HashMap<String, Planta> map = new HashMap<>();

        map.put("Diente de león", new Planta("Diente de león", "Herbáceas"));
        map.put("Lechuga", new Planta("Lechuga", "Hortalizas"));
        map.put("Manzanilla", new Planta("Manzanilla", "Herbáceas"));
        map.put("Papa", new Planta("Papa", "Tubérculos"));
        map.put("Perejil", new Planta("Perejil", "Verduras"));
        map.put("Repollo", new Planta("Repollo", "Verduras"));
        map.put("Tomate", new Planta("Tomate", "Hortalizas"));
        map.put("Zanahoria", new Planta("Zanahoria", "Tubérculos"));

        return map;
    }

    public HashMap<String, Categoria> getCatergorias () {
        HashMap<String, Categoria> map = new HashMap<>();

        map.put("Agua", new Agua("Agua", 100, "lt"));
        map.put("Abono", new Abono("Abono", 150, "gr"));
        map.put("Energía", new Energia("Energía", 500, "kWh"));

        return map;
    }

    public HashMap<Integer, Registro> getRegistros() {
        HashMap<Integer, Registro> map = new HashMap<>();

        return  map;
    }

    public boolean putRegistro(Registro registro) {
        return true;
    }
}
