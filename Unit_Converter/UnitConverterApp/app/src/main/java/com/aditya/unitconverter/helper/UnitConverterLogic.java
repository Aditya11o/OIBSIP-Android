package com.aditya.unitconverter.helper;

import com.aditya.unitconverter.model.Category;
import java.util.HashMap;
import java.util.Map;

public final class UnitConverterLogic {

    private static final Map<String, Double> LENGTH_FACTORS = new HashMap<>();
    private static final Map<String, Double> WEIGHT_FACTORS = new HashMap<>();

    static {
        // Length factors to Base Unit (Meter)
        LENGTH_FACTORS.put("Millimeter (mm)", 0.001);
        LENGTH_FACTORS.put("Centimeter (cm)", 0.01);
        LENGTH_FACTORS.put("Meter (m)", 1.0);
        LENGTH_FACTORS.put("Kilometer (km)", 1000.0);
        LENGTH_FACTORS.put("Inch (in)", 0.0254);
        LENGTH_FACTORS.put("Foot (ft)", 0.3048);
        LENGTH_FACTORS.put("Mile (mi)", 1609.344);

        // Weight factors to Base Unit (Gram)
        WEIGHT_FACTORS.put("Milligram (mg)", 0.001);
        WEIGHT_FACTORS.put("Gram (g)", 1.0);
        WEIGHT_FACTORS.put("Kilogram (kg)", 1000.0);
        WEIGHT_FACTORS.put("Ounce (oz)", 28.349523125);
        WEIGHT_FACTORS.put("Pound (lb)", 453.59237);
    }

    private UnitConverterLogic() {
        // Prevent instantiation
    }

    public static double convert(double value, Category category, String sourceUnit, String targetUnit) {
        if (sourceUnit != null && sourceUnit.equals(targetUnit)) {
            return value;
        }

        switch (category) {
            case LENGTH:
                return convertLength(value, sourceUnit, targetUnit);
            case WEIGHT:
                return convertWeight(value, sourceUnit, targetUnit);
            case TEMPERATURE:
                return convertTemperature(value, sourceUnit, targetUnit);
            default:
                return value;
        }
    }

    private static double convertLength(double value, String sourceUnit, String targetUnit) {
        Double sourceFactor = LENGTH_FACTORS.get(sourceUnit);
        Double targetFactor = LENGTH_FACTORS.get(targetUnit);

        if (sourceFactor == null || targetFactor == null) {
            return value;
        }

        // 1. Convert source to meters
        double meters = value * sourceFactor;
        // 2. Convert meters to target
        return meters / targetFactor;
    }

    private static double convertWeight(double value, String sourceUnit, String targetUnit) {
        Double sourceFactor = WEIGHT_FACTORS.get(sourceUnit);
        Double targetFactor = WEIGHT_FACTORS.get(targetUnit);

        if (sourceFactor == null || targetFactor == null) {
            return value;
        }

        // 1. Convert source to grams
        double grams = value * sourceFactor;
        // 2. Convert grams to target
        return grams / targetFactor;
    }

    private static double convertTemperature(double value, String sourceUnit, String targetUnit) {
        if (sourceUnit == null || targetUnit == null) {
            return value;
        }

        // Convert source to Celsius first
        double celsius;
        if (sourceUnit.contains("Celsius")) {
            celsius = value;
        } else if (sourceUnit.contains("Fahrenheit")) {
            celsius = (value - 32.0) * (5.0 / 9.0);
        } else if (sourceUnit.contains("Kelvin")) {
            celsius = value - 273.15;
        } else {
            celsius = value;
        }

        // Convert Celsius to target unit
        if (targetUnit.contains("Celsius")) {
            return celsius;
        } else if (targetUnit.contains("Fahrenheit")) {
            return (celsius * (9.0 / 5.0)) + 32.0;
        } else if (targetUnit.contains("Kelvin")) {
            return celsius + 273.15;
        }

        return value;
    }
}
