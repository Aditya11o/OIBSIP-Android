package com.aditya.unitconverter.utils;

import com.aditya.unitconverter.constants.AppConstants;
import java.text.DecimalFormat;

public final class FormatterUtils {

    private FormatterUtils() {
        // Prevent instantiation
    }

    public static String formatResult(double rawValue, String unitLabel) {
        DecimalFormat decimalFormat = new DecimalFormat(AppConstants.DECIMAL_PATTERN);
        String formattedNumber = decimalFormat.format(rawValue);
        if (unitLabel != null && !unitLabel.trim().isEmpty()) {
            return formattedNumber + " " + unitLabel;
        }
        return formattedNumber;
    }
}
