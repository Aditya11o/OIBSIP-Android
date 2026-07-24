package com.aditya.quizapp;

import com.aditya.quizapp.helper.JsonHelper;
import com.aditya.quizapp.model.Question;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class JsonHelperTest {

    @Test
    public void testFallbackQuestions_ReturnsNonEmptyList() {
        List<Question> fallback = JsonHelper.getFallbackQuestions();
        assertNotNull(fallback);
        assertFalse(fallback.isEmpty());
        assertTrue(fallback.size() >= 2);

        Question q1 = fallback.get(0);
        assertNotNull(q1.getQuestionText());
        assertNotNull(q1.getOptionA());
        assertTrue(q1.getCorrectAnswer() >= 1 && q1.getCorrectAnswer() <= 4);
    }
}
