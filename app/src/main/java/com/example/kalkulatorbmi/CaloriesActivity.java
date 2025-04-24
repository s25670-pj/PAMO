package com.example.kalkulatorbmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class CaloriesActivity extends AppCompatActivity {

    private EditText editTextWeight;
    private EditText editTextHeight;
    private EditText editTextAge;
    private RadioGroup radioGroupGender;
    private RadioGroup radioGroupActivity;
    private RadioButton radioButtonSedentary;
    private RadioButton radioButtonLightlyActive;
    private RadioButton radioButtonModeratelyActive;
    private RadioButton radioButtonVeryActive;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextAge = findViewById(R.id.editTextAge);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioGroupActivity = findViewById(R.id.radioGroupActivity);
        radioButtonSedentary = findViewById(R.id.radioButtonSedentary);
        radioButtonLightlyActive = findViewById(R.id.radioButtonLightlyActive);
        radioButtonModeratelyActive = findViewById(R.id.radioButtonModeratelyActive);
        radioButtonVeryActive = findViewById(R.id.radioButtonVeryActive);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCalories();
            }
        });
    }

    private void calculateCalories() {
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();
        String ageStr = editTextAge.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Proszę wypełnić wszystkie pola", Toast.LENGTH_SHORT).show();
            return;
        }

        if (radioGroupGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Proszę wybrać płeć", Toast.LENGTH_SHORT).show();
            return;
        }

        if (radioGroupActivity.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Proszę wybrać poziom aktywności", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);
            float age = Float.parseFloat(ageStr);

            if (weight <= 0 || height <= 0 || age <= 0) {
                Toast.makeText(this, "Wartości muszą być większe od zera", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obliczanie BMR (Basal Metabolic Rate) za pomocą wzoru Harrisa-Benedicta
            float bmr;
            if (radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale) {
                bmr = (float) (66.5 + (13.75 * weight) + (5.003 * height) - (6.75 * age));
            } else {
                bmr = (float) (655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age));
            }

            float activityFactor = 1.2f;

            if (radioButtonSedentary.isChecked()) {
                activityFactor = 1.2f;
            } else if (radioButtonLightlyActive.isChecked()) {
                activityFactor = 1.375f;
            } else if (radioButtonModeratelyActive.isChecked()) {
                activityFactor = 1.55f;
            } else if (radioButtonVeryActive.isChecked()) {
                activityFactor = 1.725f;
            }

            float dailyCalories = bmr * activityFactor;

            DecimalFormat df = new DecimalFormat("0");
            String caloriesResult = df.format(dailyCalories);

            textViewResult.setText("Twoje dzienne zapotrzebowanie kaloryczne: " + caloriesResult + " kcal");

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Nieprawidłowy format liczby", Toast.LENGTH_SHORT).show();
        }
    }
}