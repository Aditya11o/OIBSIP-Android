package com.aditya.unitconverter.helper;

import com.aditya.unitconverter.R;
import com.aditya.unitconverter.constants.AppConstants;
import com.aditya.unitconverter.model.Category;

public final class ValidationHelper {

    private ValidationHelper() {
        // Prevent instantiation
    }

    public static ValidationResult validate(String inputString, Category category, String sourceUnit) {
        // 1. Empty or null check
        if (inputString == null || inputString.trim().isEmpty()) {
            return ValidationResult.failure(R.string.error_empty_input);
        }

        // 2. Parse numeric value
        double value;
        try {
            value = Double.parseDouble(inputString.trim());
        } catch (NumberFormatException e) {
            return ValidationResult.failure(R.string.error_invalid_number);
        }

        // 3. Overflow check
        if (Math.abs(value) > AppConstants.MAX_CONVERSION_VALUE) {
            return ValidationResult.failure(R.string.error_overflow);
        }

        // 4. Category-specific physical limits check
        if (category == Category.LENGTH || category == Category.WEIGHT) {
            if (value < 0) {
                return ValidationResult.failure(R.string.error_negative_value);
            }
        } else if (category == Category.TEMPERATURE) {
            if (sourceUnit != null) {
                if (sourceUnit.contains("Celsius") && value < AppConstants.ABSOLUTE_ZERO_CELSIUS) {
                    return ValidationResult.failure(R.string.error_absolute_zero);
                } else if (sourceUnit.contains("Fahrenheit") && value < AppConstants.ABSOLUTE_ZERO_FAHRENHEIT) {
                    return ValidationResult.failure(R.string.error_absolute_zero);
                } else if (sourceUnit.contains("Kelvin") && value < AppConstants.ABSOLUTE_ZERO_KELVIN) {
                    return ValidationResult.failure(R.string.error_absolute_zero);
                }
            }
        }

        return ValidationResult.success();
    }
}
