package com.aditya.stopwatch;

import com.aditya.stopwatch.engine.StopwatchEngine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StopwatchEngineTest {

    private StopwatchEngine engine;

    @Before
    public void setUp() {
        engine = new StopwatchEngine();
    }

    @Test
    public void testInitialization_StateIsStopped() {
        assertFalse(engine.isRunning());
        assertEquals(0L, engine.getElapsedTime());
    }

    @Test
    public void testStart_StateIsRunning() {
        engine.start();
        assertTrue(engine.isRunning());
    }

    @Test
    public void testPause_FreezesAccumulatedTime() throws InterruptedException {
        engine.start();
        Thread.sleep(100);
        engine.pause();

        assertFalse(engine.isRunning());
        long elapsed1 = engine.getElapsedTime();
        assertTrue(elapsed1 >= 90L);

        Thread.sleep(50);
        long elapsed2 = engine.getElapsedTime();
        assertEquals(elapsed1, elapsed2);
    }

    @Test
    public void testReset_ClearsElapsedTime() {
        engine.start();
        engine.pause();
        engine.reset();

        assertFalse(engine.isRunning());
        assertEquals(0L, engine.getElapsedTime());
    }
}
