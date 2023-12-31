package com.example.green_garden_g3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.green_garden_g3.modelo.Dao;
import com.example.green_garden_g3.modelo.MaximumConsumption;
import com.example.green_garden_g3.modelo.Statistic;
import com.example.green_garden_g3.modelo.StatisticConsumption;

import java.io.File;
import java.text.DecimalFormat;

public class Estadisticas extends AppCompatActivity {


    TableLayout tableConsumptions, tableMaximum;
    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    DecimalFormat numberFormat = new DecimalFormat("#,##0");
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        tableConsumptions = findViewById(R.id.tableConsumtion);
        //tableMaximum = findViewById(R.id.tableMaximum);
        Button menu = findViewById(R.id.regresar);
        TextView textViewTotal = findViewById(R.id.textViewTotal);

        menu.setOnClickListener(view -> finish());

        Intent receive = getIntent();
        String userId = receive.getStringExtra("idUser");

        File file = getFilesDir();
        Statistic statistic = new Dao().getStatistics(file, userId);

        for (StatisticConsumption s : statistic.getStatisticConsumptions()) {
            fillTable(s);
        }
/*for (MaximumConsumption s : statistic.getMaximumConsumptions()) {
            fillTable(s);
        }*/

        textViewTotal.setText("$ " + numberFormat.format(statistic.getTotalCost()));
    }
    @SuppressLint("SetTextI18n")
    private void fillTable(StatisticConsumption nuevo) {
        TableRow row = new TableRow(this);
        TextView year = new TextView(this);
        TextView month = new TextView(this);
        TextView category = new TextView(this);
        TextView quantity = new TextView(this);
        TextView cost = new TextView(this);

        year.setText(nuevo.getYear() + "");
        month.setText(nuevo.getMonth());
        category.setText(nuevo.getCategory().getName());
        quantity.setText(decimalFormat.format(nuevo.getQuantity()));
        cost.setText(numberFormat.format(nuevo.getTotal()));

        year.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        month.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        category.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        quantity.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        cost.setGravity(View.TEXT_ALIGNMENT_GRAVITY);

        row.addView(year);
        row.addView(month);
        row.addView(category);
        row.addView(quantity);
        row.addView(cost);

        tableConsumptions.addView(row);
    }

    @SuppressLint("SetTextI18n")
    private void fillTable(MaximumConsumption nuevo) {
        TableRow row = new TableRow(this);
        TextView year = new TextView(this);
        TextView month = new TextView(this);
        TextView category = new TextView(this);
        TextView quantity = new TextView(this);

        year.setText(nuevo.getYear() + "");
        month.setText(nuevo.getMonth());
        category.setText(nuevo.getCategory().getName());
        quantity.setText(decimalFormat.format(nuevo.getQuantity()));

        year.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        month.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        category.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        quantity.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);

        row.addView(year);
        row.addView(month);
        row.addView(category);
        row.addView(quantity);

        tableMaximum.addView(row);
    }
}