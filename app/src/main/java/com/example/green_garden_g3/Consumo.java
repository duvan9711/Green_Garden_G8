package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.green_garden_g3.modelo.Category;
import com.example.green_garden_g3.modelo.Consumption;
import com.example.green_garden_g3.modelo.Dao;
import com.example.green_garden_g3.modelo.Plant;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Consumo extends AppCompatActivity {

    Dao dao = new Dao();
    private HashMap<String, Plant> plants;
    private HashMap<String, Category> category;

    private EditText fecha;
    private EditText planta;
    private EditText categoria;
    private EditText cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo);

        plants = dao.getPlants();
        category = dao.getCategory();

        Button retro = findViewById(R.id.btn_menu);
        Button guardar = findViewById(R.id.btn_guardar);
        Intent regresar = new Intent(getApplicationContext(), Menu.class);
        retro.setOnClickListener(view -> startActivity(regresar));

        planta = findViewById(R.id.ET_planta);
        categoria = findViewById(R.id.ET_categoria);
        cantidad = findViewById(R.id.ET_cantidad);

        fecha = findViewById(R.id.ET_fecha);
        fecha.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Consumo.this, (view1, year1, month1, dayOfMonth) -> {
                String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                fecha.setText(selectedDate);
            }, year, month, day);

            datePickerDialog.show();
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = System.currentTimeMillis();
                String input_date = fecha.getText().toString();
                String input_plant = planta.getText().toString();
                String input_category = categoria.getText().toString();
                double input_quantity = Double.parseDouble(cantidad.getText().toString());
                double input_cost = category.get(input_category).getCost();

                saveData(id, input_date, input_plant, input_category, input_quantity, input_cost);
            }
        });
    }

    private void saveData(long id, String inputDate, String inputPlant, String inputCategory, double inputQuantity, double inputCost) {
        String userId = "i2725r7611";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Consumption consumption = new Consumption(id, date, inputPlant, inputCategory, inputQuantity, inputCost, userId);

        File file = new File(getFilesDir(), "consumption.txt");
        dao.saveConsumption(file, consumption);
    }
}