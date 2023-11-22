package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        CardView consum = findViewById(R.id.CV_consumo);
        CardView stadist = findViewById(R.id.CV_estadistica);
        CardView consejs = findViewById(R.id.CV_consejos);
        CardView cerrar = findViewById(R.id.CV_cerrar);
        Intent cerrarSesion = new Intent(getApplicationContext(), MainActivity.class);
        Intent consumo = new Intent(getApplicationContext(), Consumo.class);
        Intent estadistica = new Intent(getApplicationContext(), Estadisticas.class);
        Intent consejos = new Intent(getApplicationContext(), Consejos.class);
        consum.setOnClickListener(view -> startActivity(consumo));
        stadist.setOnClickListener(view -> startActivity(estadistica));
        consejs.setOnClickListener(view -> startActivity(consejos));
        cerrar.setOnClickListener(view -> startActivity(cerrarSesion));
    }

    }
