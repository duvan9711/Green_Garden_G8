package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.green_garden_g3.databinding.ActivityConsejosAbonoBinding;

public class ConsejosAbono extends AppCompatActivity {

    private ActivityConsejosAbonoBinding binding;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos_abono);

        binding = ActivityConsejosAbonoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        regresar = binding.btnRegresar;
        regresar.setOnClickListener(view -> finish());
    }
}