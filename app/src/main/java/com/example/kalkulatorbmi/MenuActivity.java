package com.example.kalkulatorbmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        Button btnCalculateCalories = findViewById(R.id.btnCalculateCalories);
        Button btnDietRecommendations = findViewById(R.id.btnDietRecommendations);
        Button btnShoppingList = findViewById(R.id.btnShoppingList);
        Button btnBMIChart = findViewById(R.id.btnBMIChart);

        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCalculateCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CaloriesActivity.class);
                startActivity(intent);
            }
        });

        btnDietRecommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RecipesActivity.class);
                startActivity(intent);
            }
        });

        btnShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ShoppingListActivity.class);
                intent.putExtra("RECIPE_NAME", "Sałatka z quinoa i warzywami");
                startActivity(intent);
            }
        });

        btnBMIChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, BMIChartActivity.class);
                startActivity(intent);
            }
        });
    }
}