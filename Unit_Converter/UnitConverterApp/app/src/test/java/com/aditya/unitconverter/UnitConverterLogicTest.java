package com.aditya.unitconverter;

import com.aditya.unitconverter.helper.UnitConverterLogic;
import com.aditya.unitconverter.model.Category;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnitConverterLogicTest {

    private static final double DELTA = 1e-4;

    @Test
    public void testLengthConversion_KilometerToMeter() {
        double result = UnitConverterLogic.convert(1.5, Category.LENGTH, "Kilometer (km)", "Meter (m)");
        assertEquals(1500.0, result, DELTA);
    }

    @Test
    public void testLengthConversion_MeterToFoot() {
        double result = UnitConverterLogic.convert(1.0, Category.LENGTH, "Meter (m)", "Foot (ft)");
        assertEquals(3.28084, result, DELTA);
    }

    @Test
    public void testWeightConversion_KilogramToGram() {
        double result = UnitConverterLogic.convert(2.5, Category.WEIGHT, "Kilogram (kg)", "Gram (g)");
        assertEquals(2500.0, result, DELTA);
    }

    @Test
    public void testWeightConversion_KilogramToPound() {
        double result = UnitConverterLogic.convert(1.0, Category.WEIGHT, "Kilogram (kg)", "Pound (lb)");
        assertEquals(2.20462, result, DELTA);
    }

    @Test
    public void testTemperatureConversion_CelsiusToFahrenheit() {
        double result = UnitConverterLogic.convert(0.0, Category.TEMPERATURE, "Celsius (°C)", "Fahrenheit (°F)");
        assertEquals(32.0, result, DELTA);
    }

    @Test
    public void testTemperatureConversion_CelsiusToKelvin() {
        double result = UnitConverterLogic.convert(100.0, Category.TEMPERATURE, "Celsius (°C)", "Kelvin (K)");
        assertEquals(373.15, result, DELTA);
    }

    @Test
    public void testTemperatureConversion_FahrenheitToCelsius() {
        double result = UnitConverterLogic.convert(212.0, Category.TEMPERATURE, "Fahrenheit (°F)", "Celsius (°C)");
        assertEquals(100.0, result, DELTA);
    }
}
