package com.example.kalkulatorbmi;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kalkulatorbmi.model.BMIRecord;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BMIChartActivity extends AppCompatActivity {

    private LineChart bmiChart;
    private List<BMIRecord> bmiRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_chart);

        bmiChart = findViewById(R.id.bmiChart);
        Button btnBackToMenu = findViewById(R.id.btnBackToMenu);

        generateMockBMIData();

        setupChart();
        displayBMIData();

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void generateMockBMIData() {
        bmiRecords = new ArrayList<>();
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, -6);

        for (int i = 0; i < 12; i++) {
            float bmi = 20 + random.nextFloat() * 8;
            String status = getBMIStatus(bmi);

            bmiRecords.add(new BMIRecord(calendar.getTime(), bmi, status));

            calendar.add(Calendar.WEEK_OF_YEAR, 2);
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

    private void setupChart() {
        bmiChart.setDrawGridBackground(false);
        bmiChart.setDrawBorders(false);
        bmiChart.setAutoScaleMinMaxEnabled(true);

        Description description = new Description();
        description.setText("");
        bmiChart.setDescription(description);

        XAxis xAxis = bmiChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());

            @Override
            public String getFormattedValue(float value) {
                if (value >= 0 && value < bmiRecords.size()) {
                    return dateFormat.format(bmiRecords.get((int) value).getDate());
                }
                return "";
            }
        });

        YAxis leftAxis = bmiChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(18f);
        leftAxis.setAxisMaximum(31f);

        YAxis rightAxis = bmiChart.getAxisRight();
        rightAxis.setEnabled(false);

        LimitLine lowLine = new LimitLine(18.5f, "Niedowaga");
        lowLine.setLineColor(Color.BLUE);
        lowLine.setLineWidth(1f);
        lowLine.setTextSize(10f);

        LimitLine normalLine = new LimitLine(25f, "Nadwaga");
        normalLine.setLineColor(Color.YELLOW);
        normalLine.setLineWidth(1f);
        normalLine.setTextSize(10f);

        LimitLine highLine = new LimitLine(30f, "Otyłość");
        highLine.setLineColor(Color.RED);
        highLine.setLineWidth(1f);
        highLine.setTextSize(10f);

        leftAxis.addLimitLine(lowLine);
        leftAxis.addLimitLine(normalLine);
        leftAxis.addLimitLine(highLine);
    }

    private void displayBMIData() {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < bmiRecords.size(); i++) {
            BMIRecord record = bmiRecords.get(i);
            entries.add(new Entry(i, record.getBmiValue()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "BMI");
        dataSet.setColor(Color.rgb(65, 105, 225));
        dataSet.setLineWidth(2f);
        dataSet.setCircleColor(Color.rgb(65, 105, 225));
        dataSet.setCircleRadius(4f);
        dataSet.setDrawValues(true);
        dataSet.setValueTextSize(10f);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(dataSet);
        bmiChart.setData(lineData);
        bmiChart.invalidate();
    }
}