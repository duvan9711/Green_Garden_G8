package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    CardView cerrar, consum, stadist, consejs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        consum = findViewById(R.id.CV_consumo);
        stadist = findViewById(R.id.CV_estadistica);
        consejs = findViewById(R.id.CV_consejos);
        cerrar = findViewById(R.id.CV_cerrar);

        Intent consumo = new Intent(getApplicationContext(), Consumo.class);
        Intent estadistica = new Intent(getApplicationContext(), Estadisticas.class);
        Intent consejos = new Intent(getApplicationContext(), Consejos.class);
        Intent receive = getIntent();

        String id = receive.getStringExtra("idUser");
        consumo.putExtra("idUser", id);
        estadistica.putExtra("idUser", id);

        consum.setOnClickListener(view -> startActivity(consumo));
        stadist.setOnClickListener(view -> startActivity(estadistica));
        consejs.setOnClickListener(view -> startActivity(consejos));
        cerrar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Haz finalizado la sesión", Toast.LENGTH_LONG).show();
            finish();
        });
    }


}
