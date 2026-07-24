package com.aditya.stopwatch.utils;

import java.util.Locale;

public final class TimeFormatter {

    private TimeFormatter() {
        // Prevent instantiation
    }

    public static String format(long totalMillis) {
        if (totalMillis < 0) {
            totalMillis = 0;
        }

        long hours = totalMillis / (1000 * 60 * 60);
        long minutes = (totalMillis / (1000 * 60)) % 60;
        long seconds = (totalMillis / 1000) % 60;
        long hundredths = (totalMillis % 1000) / 10;

        if (hours > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d.%02d", hours, minutes, seconds, hundredths);
        } else {
            return String.format(Locale.US, "%02d:%02d.%02d", minutes, seconds, hundredths);
        }
    }
}
