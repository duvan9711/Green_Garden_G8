package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.green_garden_g3.databinding.ActivityConsejosBinding;

public class Consejos extends AppCompatActivity {

    private ActivityConsejosBinding binding;

    Button menu, agua, energia, abono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        binding = ActivityConsejosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        agua = binding.btnAgua;
        energia = binding.btnEnergia;
        abono = binding.btnAbono;
        menu = binding.volver;

        Intent consejosAgua = new Intent(getApplicationContext(), ConsejosAgua.class);
        agua.setOnClickListener(view -> startActivity(consejosAgua));

        Intent consejosEnergia = new Intent(getApplicationContext(), ConsejosEnergia.class);
        energia.setOnClickListener(view -> startActivity(consejosEnergia));

        Intent consejosAbono = new Intent(getApplicationContext(), ConsejosAbono.class);
        abono.setOnClickListener(view -> startActivity(consejosAbono));

        menu.setOnClickListener(view -> finish());
    }
}