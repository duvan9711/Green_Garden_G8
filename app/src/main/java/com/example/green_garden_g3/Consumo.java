package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.green_garden_g3.modelo.Category;
import com.example.green_garden_g3.modelo.Consumption;
import com.example.green_garden_g3.modelo.Dao;
import com.example.green_garden_g3.modelo.Plant;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Consumo extends AppCompatActivity {

    String userId = "";
    Dao dao = new Dao();
    private HashMap<String, Plant> plants;
    private HashMap<String, Category> categories;

    private EditText fecha;
    //private EditText planta;
    private AutoCompleteTextView planta;
    //private EditText categoria;
    private AutoCompleteTextView categoria;
    private EditText cantidad;
    private TextInputLayout tilPlant, tilCategoty, tilQuantity, tilDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo);

        plants = dao.getPlants();
        categories = dao.getCategory();

        Intent receive = getIntent();
        userId = receive.getStringExtra("idUser");

        tilPlant = findViewById(R.id.tilPlant);
        tilCategoty = findViewById(R.id.tilCategory);
        tilQuantity = findViewById(R.id.tilQuantity);
        tilDate = findViewById(R.id.tilDate);

        Button retro = findViewById(R.id.btn_menu);
        Button guardar = findViewById(R.id.btn_guardar);
        retro.setOnClickListener(view -> finish());

        String[] plantList = plants.keySet().toArray(new String[0]);
        String[] categoryList = categories.keySet().toArray(new String[0]);

        ArrayAdapter<String> plantAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plantList);
        planta = findViewById(R.id.autocomplete_plant);
        planta.setAdapter(plantAdapter);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryList);
        categoria = findViewById(R.id.autocomplete_category);
        categoria.setAdapter(categoryAdapter);

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
                    double totalCost = categories.get(inputCategory).getCost();

                    saveData(id, inputDate, inputPlant, inputCategory, quantity, totalCost);
                    cleanView();
                    setFocusEdit();
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setFocusEdit() {
        planta.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(planta, InputMethodManager.SHOW_IMPLICIT);
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
            tilDate.setError("Fecha requerida");
            state = false;
        } else {
            tilDate.setError("");
        }

        if (planta.getText().toString().isEmpty()) {
            tilPlant.setError("Planta requerida");
            state = false;
        } else if (!plants.containsKey(planta.getText().toString())) {
            tilPlant.setError("Valor de planta no válido");
            state = false;
        } else {
            tilPlant.setError("");
        }

        if (categoria.getText().toString().isEmpty()) {
            tilCategoty.setError("Categoría requerida");
            state = false;
        } else if (!categories.containsKey(categoria.getText().toString())) {
            tilCategoty.setError("Valor de categoría no válido");
            state = false;
        } else {
            tilCategoty.setError("");
        }

        if (cantidad.getText().toString().isEmpty()) {
            tilQuantity.setError("Cantidad requerida");
            state = false;
        } else {
            tilQuantity.setError("");
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