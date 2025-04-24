package com.example.kalkulatorbmi;

import org.junit.Test;
import static org.junit.Assert.*;

public class BMICalculatorTest {

    private static class BMICalculator {
        public static float calculateBMI(float weight, float height) {
            return weight / (height * height);
        }

        public static String getBMIStatus(float bmi) {
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

    @Test
    public void calculateBMI_WithNormalValues_ReturnsCorrectBMI() {
        // Arrange
        float weight = 70.0f;
        float height = 1.75f;
        float expectedBMI = 22.86f;
        float delta = 0.01f;

        // Act
        float result = BMICalculator.calculateBMI(weight, height);

        // Assert
        assertEquals(expectedBMI, result, delta);
    }

    @Test
    public void getBMIStatus_Underweight_ReturnsNiedowaga() {
        // Arrange
        float bmi = 18.0f;
        String expected = "niedowaga";

        // Act
        String result = BMICalculator.getBMIStatus(bmi);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void getBMIStatus_NormalWeight_ReturnsOptimum() {
        // Arrange
        float bmi = 22.0f;
        String expected = "optimum";

        // Act
        String result = BMICalculator.getBMIStatus(bmi);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void getBMIStatus_Overweight_ReturnsNadwaga() {
        // Arrange
        float bmi = 27.0f;
        String expected = "nadwaga";

        // Act
        String result = BMICalculator.getBMIStatus(bmi);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void getBMIStatus_Obese_ReturnsOtylosc() {
        // Arrange
        float bmi = 32.0f;
        String expected = "otyłość";

        // Act
        String result = BMICalculator.getBMIStatus(bmi);

        // Assert
        assertEquals(expected, result);
    }
}