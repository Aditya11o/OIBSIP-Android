package com.aditya.unitconverter.constants;

public final class AppConstants {
    private AppConstants() {
        // Private constructor to prevent instantiation
    }

    public static final String DECIMAL_PATTERN = "#,##0.####";
    public static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    public static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;
    public static final double ABSOLUTE_ZERO_KELVIN = 0.0;
    public static final double MAX_CONVERSION_VALUE = 1e12;
}
