package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.Objects;

public class Consumo extends AppCompatActivity {

    String userId = "";
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

        Intent receive = getIntent();
        userId = receive.getStringExtra("idUser");

        Button retro = findViewById(R.id.btn_menu);
        Button guardar = findViewById(R.id.btn_guardar);
        retro.setOnClickListener(view -> finish());

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
                if (areValidData()) {
                    long id = System.currentTimeMillis();
                    String inputDate = fecha.getText().toString();
                    String inputPlant = planta.getText().toString();
                    String inputCategory = categoria.getText().toString();
                    String inputQuantity = cantidad.getText().toString();
                    double quantity = Double.parseDouble(inputQuantity);
                    double totalCost = category.get(inputCategory).getCost();

                    saveData(id, inputDate, inputPlant, inputCategory, quantity, totalCost);
                    cleanView();
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cleanView() {
        fecha.setText("");
        cantidad.setText("");
        categoria.setText("");
        planta.setText("");
    }

    private boolean areValidData() {
        boolean state = true;

        if (fecha.getText().toString().isEmpty()) {
            state = false;
        }
        if (planta.getText().toString().isEmpty()) {
            state = false;
        }
        if (categoria.getText().toString().isEmpty()) {
            state = false;
        }
        if (cantidad.getText().toString().isEmpty()) {
            state = false;
        }

        return state;
    }

    private void saveData(long id, String inputDate, String inputPlant, String inputCategory, double inputQuantity, double inputCost) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Consumption consumption = new Consumption(id, date, inputPlant, inputCategory, inputQuantity, inputCost, userId);

        File file = getFilesDir();
        dao.saveConsumption(file, consumption);
    }
}