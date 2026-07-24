package com.aditya.unitconverter;

import com.aditya.unitconverter.helper.ValidationHelper;
import com.aditya.unitconverter.helper.ValidationResult;
import com.aditya.unitconverter.model.Category;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidationHelperTest {

    @Test
    public void testEmptyInput_ReturnsFailure() {
        ValidationResult result = ValidationHelper.validate("", Category.LENGTH, "Meter (m)");
        assertFalse(result.isValid());
    }

    @Test
    public void testInvalidNumberFormat_ReturnsFailure() {
        ValidationResult result = ValidationHelper.validate("12.34.56", Category.LENGTH, "Meter (m)");
        assertFalse(result.isValid());
    }

    @Test
    public void testNegativeLength_ReturnsFailure() {
        ValidationResult result = ValidationHelper.validate("-10", Category.LENGTH, "Meter (m)");
        assertFalse(result.isValid());
    }

    @Test
    public void testTemperatureBelowAbsoluteZero_ReturnsFailure() {
        ValidationResult result = ValidationHelper.validate("-300", Category.TEMPERATURE, "Celsius (°C)");
        assertFalse(result.isValid());
    }

    @Test
    public void testValidInput_ReturnsSuccess() {
        ValidationResult result = ValidationHelper.validate("100.5", Category.LENGTH, "Meter (m)");
        assertTrue(result.isValid());
    }
}
