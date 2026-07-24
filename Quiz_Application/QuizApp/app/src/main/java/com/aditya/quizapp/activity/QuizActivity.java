package com.aditya.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aditya.quizapp.R;
import com.aditya.quizapp.constants.AppConstants;
import com.aditya.quizapp.helper.JsonHelper;
import com.aditya.quizapp.helper.QuizEngine;
import com.aditya.quizapp.model.Question;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class QuizActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private MaterialTextView tvQuestionCounter;
    private MaterialTextView tvQuestionText;
    private RadioGroup rgOptions;
    private RadioButton rbOptionA;
    private RadioButton rbOptionB;
    private RadioButton rbOptionC;
    private RadioButton rbOptionD;
    private MaterialButton btnNextQuestion;

    private QuizEngine quizEngine;
    private boolean isAnswerSubmitted = false;
    private int selectedOptionIndex = -1;

    private static final String KEY_IS_ANSWERED = "key_is_answered";
    private static final String KEY_SELECTED_OPTION = "key_selected_option";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initViews();
        quizEngine = new QuizEngine(JsonHelper.loadQuestions(this));

        if (savedInstanceState != null) {
            isAnswerSubmitted = savedInstanceState.getBoolean(KEY_IS_ANSWERED, false);
            selectedOptionIndex = savedInstanceState.getInt(KEY_SELECTED_OPTION, -1);
        }

        setupOptionListeners();
        setupNextButtonListener();
        renderCurrentQuestion();

        if (isAnswerSubmitted && selectedOptionIndex != -1) {
            handleAnswerSubmission(selectedOptionIndex);
        }
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        tvQuestionCounter = findViewById(R.id.tv_question_counter);
        tvQuestionText = findViewById(R.id.tv_question_text);
        rgOptions = findViewById(R.id.rg_options);
        rbOptionA = findViewById(R.id.rb_option_a);
        rbOptionB = findViewById(R.id.rb_option_b);
        rbOptionC = findViewById(R.id.rb_option_c);
        rbOptionD = findViewById(R.id.rb_option_d);
        btnNextQuestion = findViewById(R.id.btn_next_question);
    }

    private void renderCurrentQuestion() {
        Question current = quizEngine.getCurrentQuestion();
        int currentNum = quizEngine.getCurrentIndex() + 1;
        int total = quizEngine.getTotalQuestions();

        progressBar.setMax(total);
        progressBar.setProgress(currentNum);

        tvQuestionCounter.setText(getString(R.string.question_counter_format, currentNum, total));
        tvQuestionText.setText(current.getQuestionText());

        rbOptionA.setText(current.getOptionA());
        rbOptionB.setText(current.getOptionB());
        rbOptionC.setText(current.getOptionC());
        rbOptionD.setText(current.getOptionD());

        resetOptionStyles();
    }

    private void resetOptionStyles() {
        rgOptions.clearCheck();
        isAnswerSubmitted = false;
        selectedOptionIndex = -1;

        RadioButton[] buttons = {rbOptionA, rbOptionB, rbOptionC, rbOptionD};
        for (RadioButton rb : buttons) {
            rb.setEnabled(true);
            rb.setBackgroundResource(R.drawable.bg_option_default);
            rb.setTextColor(getColor(R.color.on_surface));
        }

        btnNextQuestion.setEnabled(false);
        if (quizEngine.hasMoreQuestions()) {
            btnNextQuestion.setText(R.string.btn_next_question);
        } else {
            btnNextQuestion.setText(R.string.btn_finish_quiz);
        }
    }

    private void setupOptionListeners() {
        rbOptionA.setOnClickListener(v -> onOptionClicked(1));
        rbOptionB.setOnClickListener(v -> onOptionClicked(2));
        rbOptionC.setOnClickListener(v -> onOptionClicked(3));
        rbOptionD.setOnClickListener(v -> onOptionClicked(4));
    }

    private void onOptionClicked(int optionIndex) {
        if (isAnswerSubmitted) return;
        selectedOptionIndex = optionIndex;
        quizEngine.submitAnswer(optionIndex);
        handleAnswerSubmission(optionIndex);
    }

    private void handleAnswerSubmission(int selectedIndex) {
        isAnswerSubmitted = true;
        Question current = quizEngine.getCurrentQuestion();
        int correctIndex = current.getCorrectAnswer();

        RadioButton[] buttons = {rbOptionA, rbOptionB, rbOptionC, rbOptionD};

        // Disable all option radio buttons to prevent multi-selection
        for (RadioButton rb : buttons) {
            rb.setEnabled(false);
        }

        // Highlight correct option in Green
        RadioButton correctRb = getRadioButtonByIndex(correctIndex);
        if (correctRb != null) {
            correctRb.setBackgroundResource(R.drawable.bg_option_correct);
            correctRb.setTextColor(getColor(R.color.option_correct_text));
        }

        // If user selection was wrong, highlight selected option in Red
        if (selectedIndex != correctIndex) {
            RadioButton selectedRb = getRadioButtonByIndex(selectedIndex);
            if (selectedRb != null) {
                selectedRb.setBackgroundResource(R.drawable.bg_option_wrong);
                selectedRb.setTextColor(getColor(R.color.option_wrong_text));
            }
        }

        btnNextQuestion.setEnabled(true);
    }

    private RadioButton getRadioButtonByIndex(int index) {
        switch (index) {
            case 1: return rbOptionA;
            case 2: return rbOptionB;
            case 3: return rbOptionC;
            case 4: return rbOptionD;
            default: return null;
        }
    }

    private void setupNextButtonListener() {
        btnNextQuestion.setOnClickListener(v -> {
            if (quizEngine.hasMoreQuestions()) {
                quizEngine.nextQuestion();
                renderCurrentQuestion();
            } else {
                navigateToResults();
            }
        });
    }

    private void navigateToResults() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(AppConstants.EXTRA_SCORE, quizEngine.getScore());
        intent.putExtra(AppConstants.EXTRA_TOTAL_QUESTIONS, quizEngine.getTotalQuestions());
        intent.putExtra(AppConstants.EXTRA_CORRECT_COUNT, quizEngine.getCorrectCount());
        intent.putExtra(AppConstants.EXTRA_WRONG_COUNT, quizEngine.getWrongCount());
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IS_ANSWERED, isAnswerSubmitted);
        outState.getInt(KEY_SELECTED_OPTION, selectedOptionIndex);
    }
}
