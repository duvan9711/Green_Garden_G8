package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.green_garden_g3.databinding.ActivityConsejosEnergiaBinding;

public class ConsejosEnergia extends AppCompatActivity {

    private ActivityConsejosEnergiaBinding binding;
    Button regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos_energia);

        binding = ActivityConsejosEnergiaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        regresar = binding.btnRegresar;
        Intent retroceder = new Intent(getApplicationContext(), Consejos.class);
        regresar.setOnClickListener(view -> startActivity(retroceder));

    }
}