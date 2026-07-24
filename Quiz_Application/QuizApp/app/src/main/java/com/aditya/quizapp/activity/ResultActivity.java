package com.aditya.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.aditya.quizapp.R;
import com.aditya.quizapp.constants.AppConstants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class ResultActivity extends AppCompatActivity {

    private MaterialTextView tvBadge;
    private MaterialTextView tvFinalScore;
    private MaterialTextView tvPercentage;
    private MaterialTextView tvCorrectCount;
    private MaterialTextView tvWrongCount;
    private MaterialButton btnRestartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initViews();
        displayResults();
        setupRestartButtonListener();
    }

    private void initViews() {
        tvBadge = findViewById(R.id.tv_badge);
        tvFinalScore = findViewById(R.id.tv_final_score);
        tvPercentage = findViewById(R.id.tv_percentage);
        tvCorrectCount = findViewById(R.id.tv_correct_count);
        tvWrongCount = findViewById(R.id.tv_wrong_count);
        btnRestartQuiz = findViewById(R.id.btn_restart_quiz);
    }

    private void displayResults() {
        Intent intent = getIntent();
        int score = intent.getIntExtra(AppConstants.EXTRA_SCORE, 0);
        int total = intent.getIntExtra(AppConstants.EXTRA_TOTAL_QUESTIONS, 10);
        int correct = intent.getIntExtra(AppConstants.EXTRA_CORRECT_COUNT, 0);
        int wrong = intent.getIntExtra(AppConstants.EXTRA_WRONG_COUNT, 0);

        int percentage = total > 0 ? (int) Math.round(((double) score / total) * 100) : 0;

        tvFinalScore.setText(getString(R.string.score_format, score, total));
        tvPercentage.setText(getString(R.string.percentage_format, percentage));
        tvCorrectCount.setText(getString(R.string.label_correct, correct));
        tvWrongCount.setText(getString(R.string.label_wrong, wrong));

        // Select Performance Badge based on percentage
        if (percentage >= 80) {
            tvBadge.setText(R.string.badge_master);
            tvBadge.setTextColor(getColor(R.color.option_correct_border));
        } else if (percentage >= 50) {
            tvBadge.setText(R.string.badge_good);
            tvBadge.setTextColor(getColor(R.color.primary));
        } else {
            tvBadge.setText(R.string.badge_practice);
            tvBadge.setTextColor(getColor(R.color.option_wrong_border));
        }
    }

    private void setupRestartButtonListener() {
        btnRestartQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
