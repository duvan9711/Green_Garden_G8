package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton consum = findViewById(R.id.img_consumo);
        ImageButton stadist = findViewById(R.id.img_estadistica);
        ImageButton consejs = findViewById(R.id.img_consejo);
        Button cerrar = findViewById(R.id.cerrar);
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
