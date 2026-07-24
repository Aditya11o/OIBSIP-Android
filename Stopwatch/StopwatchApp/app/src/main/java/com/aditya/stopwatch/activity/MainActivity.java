package com.aditya.stopwatch.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.stopwatch.R;
import com.aditya.stopwatch.adapter.LapAdapter;
import com.aditya.stopwatch.constants.AppConstants;
import com.aditya.stopwatch.constants.StopwatchState;
import com.aditya.stopwatch.engine.StopwatchEngine;
import com.aditya.stopwatch.model.Lap;
import com.aditya.stopwatch.utils.TimeFormatter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StopwatchEngine.OnTickListener {

    private MaterialTextView tvTimerDisplay;
    private MaterialButton btnStart;
    private MaterialButton btnPause;
    private MaterialButton btnReset;
    private MaterialButton btnLap;
    private MaterialTextView tvEmptyLaps;
    private RecyclerView rvLaps;

    private StopwatchEngine stopwatchEngine;
    private LapAdapter lapAdapter;
    private StopwatchState currentState = StopwatchState.RESET;

    private long lastLapTotalTimeMs = 0L;
    private long lastClickTimestamp = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();

        stopwatchEngine = new StopwatchEngine();
        stopwatchEngine.setOnTickListener(this);

        setupButtonListeners();

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        } else {
            updateButtonStates(StopwatchState.RESET);
        }
    }

    private void initViews() {
        tvTimerDisplay = findViewById(R.id.tv_timer_display);
        btnStart = findViewById(R.id.btn_start);
        btnPause = findViewById(R.id.btn_pause);
        btnReset = findViewById(R.id.btn_reset);
        btnLap = findViewById(R.id.btn_lap);
        tvEmptyLaps = findViewById(R.id.tv_empty_laps);
        rvLaps = findViewById(R.id.rv_laps);
    }

    private void setupRecyclerView() {
        lapAdapter = new LapAdapter();
        rvLaps.setLayoutManager(new LinearLayoutManager(this));
        rvLaps.setAdapter(lapAdapter);
    }

    private void setupButtonListeners() {
        btnStart.setOnClickListener(v -> {
            if (isDebounced()) return;
            onStartClicked();
        });

        btnPause.setOnClickListener(v -> {
            if (isDebounced()) return;
            onPauseClicked();
        });

        btnReset.setOnClickListener(v -> {
            if (isDebounced()) return;
            onResetClicked();
        });

        btnLap.setOnClickListener(v -> {
            if (isDebounced()) return;
            onLapClicked();
        });
    }

    private boolean isDebounced() {
        long current = SystemClock.elapsedRealtime();
        if (current - lastClickTimestamp < AppConstants.DEBOUNCE_THRESHOLD_MS) {
            return true;
        }
        lastClickTimestamp = current;
        return false;
    }

    private void onStartClicked() {
        stopwatchEngine.start();
        updateButtonStates(StopwatchState.RUNNING);
    }

    private void onPauseClicked() {
        stopwatchEngine.pause();
        updateButtonStates(StopwatchState.PAUSED);
    }

    private void onResetClicked() {
        stopwatchEngine.reset();
        lapAdapter.clearLaps();
        lastLapTotalTimeMs = 0L;
        tvTimerDisplay.setText(getString(R.string.default_time_display));
        updateEmptyStateVisibility();
        updateButtonStates(StopwatchState.RESET);
    }

    private void onLapClicked() {
        if (currentState != StopwatchState.RUNNING) return;

        long currentElapsed = stopwatchEngine.getElapsedTime();
        long lapTimeMs = currentElapsed - lastLapTotalTimeMs;
        lastLapTotalTimeMs = currentElapsed;

        int lapNumber = lapAdapter.getItemCount() + 1;
        String lapTimeFormatted = TimeFormatter.format(lapTimeMs);
        String totalTimeFormatted = TimeFormatter.format(currentElapsed);

        Lap lap = new Lap(lapNumber, lapTimeMs, lapTimeFormatted, totalTimeFormatted);
        lapAdapter.addLap(lap);

        rvLaps.scrollToPosition(0);
        updateEmptyStateVisibility();
    }

    private void updateButtonStates(StopwatchState newState) {
        this.currentState = newState;
        switch (newState) {
            case RESET:
                btnStart.setEnabled(true);
                btnStart.setText(R.string.btn_start);
                btnPause.setEnabled(false);
                btnReset.setEnabled(false);
                btnLap.setEnabled(false);
                break;

            case RUNNING:
                btnStart.setEnabled(false);
                btnPause.setEnabled(true);
                btnReset.setEnabled(true);
                btnLap.setEnabled(true);
                break;

            case PAUSED:
                btnStart.setEnabled(true);
                btnStart.setText(R.string.btn_resume);
                btnPause.setEnabled(false);
                btnReset.setEnabled(true);
                btnLap.setEnabled(false);
                break;
        }
    }

    private void updateEmptyStateVisibility() {
        if (lapAdapter.getItemCount() == 0) {
            tvEmptyLaps.setVisibility(View.VISIBLE);
            rvLaps.setVisibility(View.GONE);
        } else {
            tvEmptyLaps.setVisibility(View.GONE);
            rvLaps.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTick(long elapsedTimeMs) {
        tvTimerDisplay.setText(TimeFormatter.format(elapsedTimeMs));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (stopwatchEngine != null) {
            stopwatchEngine.pauseTickLoop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stopwatchEngine != null && currentState == StopwatchState.RUNNING) {
            stopwatchEngine.resumeTickLoop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (stopwatchEngine != null) {
            stopwatchEngine.cleanup();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(AppConstants.KEY_ACCUMULATED_TIME, stopwatchEngine.getElapsedTime());
        outState.putString(AppConstants.KEY_STATE, currentState.name());
        outState.putSerializable(AppConstants.KEY_LAPS_LIST, lapAdapter.getLapsList());
    }

    @SuppressWarnings("unchecked")
    private void restoreInstanceState(Bundle savedInstanceState) {
        long savedTime = savedInstanceState.getLong(AppConstants.KEY_ACCUMULATED_TIME, 0L);
        String stateName = savedInstanceState.getString(AppConstants.KEY_STATE, StopwatchState.RESET.name());
        StopwatchState savedState = StopwatchState.valueOf(stateName);

        ArrayList<Lap> savedLaps = (ArrayList<Lap>) savedInstanceState.getSerializable(AppConstants.KEY_LAPS_LIST);
        if (savedLaps != null) {
            lapAdapter.setLapsList(savedLaps);
            if (!savedLaps.isEmpty()) {
                lastLapTotalTimeMs = savedLaps.get(0).getLapTimeMs();
            }
        }

        stopwatchEngine.setAccumulatedTime(savedTime);
        tvTimerDisplay.setText(TimeFormatter.format(savedTime));

        if (savedState == StopwatchState.RUNNING) {
            stopwatchEngine.start();
        }

        updateEmptyStateVisibility();
        updateButtonStates(savedState);
    }
}
