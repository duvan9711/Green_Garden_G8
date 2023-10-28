package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class Consumo extends AppCompatActivity {

    private EditText fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumo);

        Button retro = findViewById(R.id.btn_menu);
        Intent regresar = new Intent(getApplicationContext(), Menu.class);
        retro.setOnClickListener(view -> startActivity(regresar));


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
    }
}