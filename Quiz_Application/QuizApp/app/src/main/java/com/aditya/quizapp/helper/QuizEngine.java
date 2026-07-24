package com.aditya.quizapp.helper;

import com.aditya.quizapp.model.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizEngine {

    private final List<Question> questions;
    private int currentIndex = 0;
    private int correctCount = 0;
    private int wrongCount = 0;

    public QuizEngine(List<Question> questionsList) {
        if (questionsList != null && !questionsList.isEmpty()) {
            this.questions = new ArrayList<>(questionsList);
            shuffleQuestions();
        } else {
            this.questions = JsonHelper.getFallbackQuestions();
        }
    }

    public void shuffleQuestions() {
        Collections.shuffle(this.questions);
    }

    public Question getCurrentQuestion() {
        if (currentIndex >= 0 && currentIndex < questions.size()) {
            return questions.get(currentIndex);
        }
        return questions.get(0);
    }

    public boolean submitAnswer(int selectedOptionIndex) {
        Question current = getCurrentQuestion();
        boolean isCorrect = (selectedOptionIndex == current.getCorrectAnswer());
        if (isCorrect) {
            correctCount++;
        } else {
            wrongCount++;
        }
        return isCorrect;
    }

    public boolean nextQuestion() {
        if (hasMoreQuestions()) {
            currentIndex++;
            return true;
        }
        return false;
    }

    public boolean hasMoreQuestions() {
        return currentIndex < questions.size() - 1;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getScore() {
        return correctCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public void reset() {
        currentIndex = 0;
        correctCount = 0;
        wrongCount = 0;
        shuffleQuestions();
    }
}
