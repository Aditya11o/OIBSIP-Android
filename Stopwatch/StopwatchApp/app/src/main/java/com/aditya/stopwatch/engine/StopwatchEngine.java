package com.aditya.stopwatch.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

public class StopwatchEngine {

    public interface OnTickListener {
        void onTick(long elapsedTimeMs);
    }

    private static final long TICK_INTERVAL_MS = 30L;

    private final Handler handler;
    private Runnable tickRunnable;
    private OnTickListener onTickListener;

    private long startTime = 0L;
    private long accumulatedTime = 0L;
    private boolean isRunning = false;

    public StopwatchEngine() {
        this.handler = new Handler(Looper.getMainLooper());
        initRunnable();
    }

    private void initRunnable() {
        tickRunnable = new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    long currentElapsed = SystemClock.elapsedRealtime() - startTime;
                    if (onTickListener != null) {
                        onTickListener.onTick(currentElapsed);
                    }
                    handler.postDelayed(this, TICK_INTERVAL_MS);
                }
            }
        };
    }

    public void setOnTickListener(OnTickListener listener) {
        this.onTickListener = listener;
    }

    public void start() {
        if (!isRunning) {
            startTime = SystemClock.elapsedRealtime() - accumulatedTime;
            isRunning = true;
            handler.removeCallbacks(tickRunnable);
            handler.post(tickRunnable);
        }
    }

    public void pause() {
        if (isRunning) {
            isRunning = false;
            accumulatedTime = SystemClock.elapsedRealtime() - startTime;
            handler.removeCallbacks(tickRunnable);
        }
    }

    public void reset() {
        isRunning = false;
        startTime = 0L;
        accumulatedTime = 0L;
        handler.removeCallbacks(tickRunnable);
        if (onTickListener != null) {
            onTickListener.onTick(0L);
        }
    }

    public long getElapsedTime() {
        if (isRunning) {
            return SystemClock.elapsedRealtime() - startTime;
        }
        return accumulatedTime;
    }

    public void setAccumulatedTime(long timeMs) {
        this.accumulatedTime = timeMs;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void pauseTickLoop() {
        handler.removeCallbacks(tickRunnable);
    }

    public void resumeTickLoop() {
        if (isRunning) {
            handler.removeCallbacks(tickRunnable);
            handler.post(tickRunnable);
        }
    }

    public void cleanup() {
        isRunning = false;
        handler.removeCallbacksAndMessages(null);
        onTickListener = null;
    }
}
