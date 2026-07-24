package com.aditya.quizapp.constants;

public final class AppConstants {
    private AppConstants() {
        // Prevent instantiation
    }

    public static final String ASSET_QUESTIONS_FILE = "questions.json";
    
    // Intent Extra Keys
    public static final String EXTRA_SCORE = "extra_score";
    public static final String EXTRA_TOTAL_QUESTIONS = "extra_total_questions";
    public static final String EXTRA_CORRECT_COUNT = "extra_correct_count";
    public static final String EXTRA_WRONG_COUNT = "extra_wrong_count";
}
