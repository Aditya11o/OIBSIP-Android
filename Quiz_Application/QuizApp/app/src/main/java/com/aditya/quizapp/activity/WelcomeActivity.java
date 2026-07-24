package com.aditya.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.aditya.quizapp.R;
import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        MaterialButton btnStartQuiz = findViewById(R.id.btn_start_quiz);
        btnStartQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, QuizActivity.class);
            startActivity(intent);
        });
    }
}
