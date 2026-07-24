package com.aditya.quizapp.helper;

import android.content.Context;
import com.aditya.quizapp.constants.AppConstants;
import com.aditya.quizapp.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class JsonHelper {

    private JsonHelper() {
        // Prevent instantiation
    }

    public static List<Question> loadQuestions(Context context) {
        List<Question> questionList = new ArrayList<>();
        String jsonString = loadJsonFromAsset(context, AppConstants.ASSET_QUESTIONS_FILE);

        if (jsonString != null && !jsonString.isEmpty()) {
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.optInt("id", i + 1);
                    String questionText = jsonObject.getString("question");
                    String optionA = jsonObject.getString("optionA");
                    String optionB = jsonObject.getString("optionB");
                    String optionC = jsonObject.getString("optionC");
                    String optionD = jsonObject.getString("optionD");
                    int correctAnswer = jsonObject.getInt("correctAnswer");

                    questionList.add(new Question(id, questionText, optionA, optionB, optionC, optionD, correctAnswer));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                questionList = getFallbackQuestions();
            }
        } else {
            questionList = getFallbackQuestions();
        }

        return questionList;
    }

    private static String loadJsonFromAsset(Context context, String fileName) {
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Question> getFallbackQuestions() {
        List<Question> fallback = new ArrayList<>();
        fallback.add(new Question(1, "Which programming language is primarily used for native Android development alongside Kotlin?", "Python", "Java", "C#", "Swift", 2));
        fallback.add(new Question(2, "Which layout manager arranges child views in a single horizontal or vertical row in Android?", "RelativeLayout", "FrameLayout", "LinearLayout", "ConstraintLayout", 3));
        return fallback;
    }
}
