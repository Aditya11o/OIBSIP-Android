package com.aditya.quizapp.model;

public class Question {
    private final int id;
    private final String questionText;
    private final String optionA;
    private final String optionB;
    private final String optionC;
    private final String optionD;
    private final int correctAnswer; // 1 = Option A, 2 = Option B, 3 = Option C, 4 = Option D

    public Question(int id, String questionText, String optionA, String optionB, String optionC, String optionD, int correctAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
