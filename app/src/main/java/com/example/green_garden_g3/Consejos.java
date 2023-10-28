package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Consejos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        Button menu = findViewById(R.id.volver);
        Intent volver = new Intent(getApplicationContext(), Menu.class);
        menu.setOnClickListener(view -> startActivity(volver));
    }
}