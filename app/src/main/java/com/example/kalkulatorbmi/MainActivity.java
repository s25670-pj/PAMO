package com.example.kalkulatorbmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextWeight;
    private EditText editTextHeight;
    private Button buttonCalculate;
    private TextView textViewResult;
    private TextView textViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        textViewStatus = findViewById(R.id.textViewStatus);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Proszę wypełnić wszystkie pola", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr) / 100;

            if (weight <= 0 || height <= 0) {
                Toast.makeText(this, "Wartości muszą być większe od zera", Toast.LENGTH_SHORT).show();
                return;
            }

            float bmi = weight / (height * height);

            DecimalFormat df = new DecimalFormat("0.00");
            String bmiResult = df.format(bmi);

            String status = getBMIStatus(bmi);

            textViewResult.setText(getString(R.string.bmi_result, bmiResult));
            textViewStatus.setText(getString(R.string.status_result, status));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Nieprawidłowy format liczby", Toast.LENGTH_SHORT).show();
        }
    }

    private String getBMIStatus(float bmi) {
        if (bmi < 18.5) {
            return "niedowaga";
        } else if (bmi < 25) {
            return "optimum";
        } else if (bmi < 30) {
            return "nadwaga";
        } else {
            return "otyłość";
        }
    }
}