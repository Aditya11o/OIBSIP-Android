package com.aditya.unitconverter.helper;

public class ValidationResult {
    private final boolean valid;
    private final int errorMessageResId;

    private ValidationResult(boolean valid, int errorMessageResId) {
        this.valid = valid;
        this.errorMessageResId = errorMessageResId;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, 0);
    }

    public static ValidationResult failure(int errorMessageResId) {
        return new ValidationResult(false, errorMessageResId);
    }

    public boolean isValid() {
        return valid;
    }

    public int getErrorMessageResId() {
        return errorMessageResId;
    }
}
