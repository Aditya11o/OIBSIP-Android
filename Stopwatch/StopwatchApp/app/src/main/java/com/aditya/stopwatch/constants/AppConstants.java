package com.aditya.stopwatch.constants;

public final class AppConstants {
    private AppConstants() {
        // Prevent instantiation
    }

    public static final long TICK_INTERVAL_MS = 30L;
    public static final long DEBOUNCE_THRESHOLD_MS = 300L;

    // Bundle State Keys
    public static final String KEY_START_TIME = "key_start_time";
    public static final String KEY_ACCUMULATED_TIME = "key_accumulated_time";
    public static final String KEY_STATE = "key_state";
    public static final String KEY_LAPS_LIST = "key_laps_list";
}
