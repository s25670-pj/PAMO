package com.example.kalkulatorbmi.model;

import java.util.Date;

public class BMIRecord {
    private Date date;
    private float bmiValue;
    private String status;

    public BMIRecord(Date date, float bmiValue, String status) {
        this.date = date;
        this.bmiValue = bmiValue;
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public float getBmiValue() {
        return bmiValue;
    }

    public String getStatus() {
        return status;
    }
}