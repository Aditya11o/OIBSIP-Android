package com.aditya.stopwatch.model;

import java.io.Serializable;

public class Lap implements Serializable {
    private final int lapNumber;
    private final long lapTimeMs;
    private final String lapTimeFormatted;
    private final String totalTimeFormatted;

    public Lap(int lapNumber, long lapTimeMs, String lapTimeFormatted, String totalTimeFormatted) {
        this.lapNumber = lapNumber;
        this.lapTimeMs = lapTimeMs;
        this.lapTimeFormatted = lapTimeFormatted;
        this.totalTimeFormatted = totalTimeFormatted;
    }

    public int getLapNumber() {
        return lapNumber;
    }

    public long getLapTimeMs() {
        return lapTimeMs;
    }

    public String getLapTimeFormatted() {
        return lapTimeFormatted;
    }

    public String getTotalTimeFormatted() {
        return totalTimeFormatted;
    }
}
