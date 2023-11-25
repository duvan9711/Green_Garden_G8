package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.green_garden_g3.databinding.ActivityConsejosAguaBinding;

public class ConsejosAgua extends AppCompatActivity {

    private ActivityConsejosAguaBinding binding;

    Button regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos_agua);

        binding = ActivityConsejosAguaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        regresar = binding.btnRegresar;

        Intent retro = new Intent(getApplicationContext(), Consejos.class);
        regresar.setOnClickListener(view -> startActivity(retro));

    }
}