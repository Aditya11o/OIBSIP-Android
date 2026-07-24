package com.aditya.stopwatch;

import com.aditya.stopwatch.utils.TimeFormatter;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeFormatterTest {

    @Test
    public void testFormat_ZeroMillis_ReturnsDefaultFormat() {
        assertEquals("00:00.00", TimeFormatter.format(0L));
    }

    @Test
    public void testFormat_UnderOneMinute_ReturnsMinutesSecondsHundredths() {
        assertEquals("00:05.23", TimeFormatter.format(5230L));
    }

    @Test
    public void testFormat_OverOneMinute_ReturnsMinutesSecondsHundredths() {
        assertEquals("01:05.43", TimeFormatter.format(65430L));
    }

    @Test
    public void testFormat_OverOneHour_ReturnsHoursMinutesSecondsHundredths() {
        assertEquals("01:02:03.45", TimeFormatter.format(3723450L));
    }

    @Test
    public void testFormat_NegativeInput_ReturnsZeroFormat() {
        assertEquals("00:00.00", TimeFormatter.format(-1000L));
    }
}
