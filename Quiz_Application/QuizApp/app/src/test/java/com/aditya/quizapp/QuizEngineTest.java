package com.aditya.quizapp;

import com.aditya.quizapp.helper.QuizEngine;
import com.aditya.quizapp.model.Question;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuizEngineTest {

    private QuizEngine quizEngine;
    private List<Question> sampleQuestions;

    @Before
    public void setUp() {
        sampleQuestions = new ArrayList<>();
        sampleQuestions.add(new Question(1, "Question 1", "A1", "B1", "C1", "D1", 1));
        sampleQuestions.add(new Question(2, "Question 2", "A2", "B2", "C2", "D2", 2));
        sampleQuestions.add(new Question(3, "Question 3", "A3", "B3", "C3", "D3", 3));
        quizEngine = new QuizEngine(sampleQuestions);
    }

    @Test
    public void testInitialization_TotalQuestionsMatch() {
        assertEquals(3, quizEngine.getTotalQuestions());
        assertEquals(0, quizEngine.getCurrentIndex());
        assertEquals(0, quizEngine.getScore());
    }

    @Test
    public void testSubmitAnswer_CorrectOption_IncrementsScore() {
        Question current = quizEngine.getCurrentQuestion();
        int correctOption = current.getCorrectAnswer();

        boolean isCorrect = quizEngine.submitAnswer(correctOption);

        assertTrue(isCorrect);
        assertEquals(1, quizEngine.getScore());
        assertEquals(1, quizEngine.getCorrectCount());
        assertEquals(0, quizEngine.getWrongCount());
    }

    @Test
    public void testSubmitAnswer_WrongOption_IncrementsWrongCount() {
        Question current = quizEngine.getCurrentQuestion();
        int wrongOption = (current.getCorrectAnswer() == 1) ? 2 : 1;

        boolean isCorrect = quizEngine.submitAnswer(wrongOption);

        assertFalse(isCorrect);
        assertEquals(0, quizEngine.getScore());
        assertEquals(0, quizEngine.getCorrectCount());
        assertEquals(1, quizEngine.getWrongCount());
    }

    @Test
    public void testNextQuestion_AdvancesIndex() {
        assertTrue(quizEngine.hasMoreQuestions());
        boolean advanced = quizEngine.nextQuestion();
        assertTrue(advanced);
        assertEquals(1, quizEngine.getCurrentIndex());
    }

    @Test
    public void testReset_ResetsScoresAndIndex() {
        quizEngine.submitAnswer(1);
        quizEngine.nextQuestion();
        quizEngine.reset();

        assertEquals(0, quizEngine.getCurrentIndex());
        assertEquals(0, quizEngine.getScore());
        assertEquals(0, quizEngine.getCorrectCount());
        assertEquals(0, quizEngine.getWrongCount());
    }
}
