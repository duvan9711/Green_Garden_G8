package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Estadisticas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        Button menu = findViewById(R.id.regresar);
        Intent retroceder = new Intent(getApplicationContext(), Menu.class);
        menu.setOnClickListener(view -> startActivity(retroceder));
    }
}