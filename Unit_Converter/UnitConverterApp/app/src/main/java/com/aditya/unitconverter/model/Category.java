package com.aditya.unitconverter.model;

import com.aditya.unitconverter.R;

public enum Category {
    LENGTH("Length", R.array.length_units_array),
    WEIGHT("Weight", R.array.weight_units_array),
    TEMPERATURE("Temperature", R.array.temperature_units_array);

    private final String displayName;
    private final int stringArrayResId;

    Category(String displayName, int stringArrayResId) {
        this.displayName = displayName;
        this.stringArrayResId = stringArrayResId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getStringArrayResId() {
        return stringArrayResId;
    }

    public static Category fromPosition(int position) {
        Category[] categories = values();
        if (position >= 0 && position < categories.length) {
            return categories[position];
        }
        return LENGTH;
    }
}
