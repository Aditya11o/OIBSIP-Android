# Quiz Application

An offline-first, production-grade Android multiple-choice quiz application built with **Java**, **Material Design 3 Components**, and a **Local JSON Asset Engine**. Developed as Task 2 for the **Oasis InfoByte Internship Program (OIBSIP)** under the `OIBSIP-Android` repository.

---

## 📱 Features

- ✔ **Welcome Screen**: Clean landing screen (`WelcomeActivity`) with hero banner and "Start Quiz" launch trigger.
- ✔ **Dynamic Question Engine**:
  - Minimum 10 Android/Tech trivia questions loaded locally from `assets/questions.json`.
  - Questions are automatically shuffled (`Collections.shuffle()`) on every app launch for a unique experience.
- ✔ **Interactive Quiz Screen**:
  - Displays Question Counter (e.g., `Question 3 of 10`) and progress bar.
  - Presents question text inside an elevated `MaterialCardView`.
  - Renders 4 option choices via `RadioGroup` and `RadioButton`.
- ✔ **Immediate Visual Feedback System**:
  - Selecting the **Correct Answer** highlights the option background **Green** (`#4CAF50`).
  - Selecting a **Wrong Answer** highlights the option **Red** (`#F44336`) and reveals the correct option in **Green**.
  - Automatically disables options after selection to prevent multiple guesses.
- ✔ **Score Tracking & Results Breakdown**:
  - Tracks correct and wrong answers in real-time.
  - Calculates accuracy percentage score.
  - Displays performance badges (🏆 Quiz Master! for $\ge 80\%$, 👍 Good Job! for $\ge 50\%$, 📚 Keep Practicing! for $< 50\%$).
- ✔ **Restart Quiz Loop**: Allows users to restart the quiz instantly with newly reshuffled questions.

---

## 🛠 Tech Stack & Specifications

- **Language**: Java 8
- **UI Framework**: XML Layouts with Material Design 3 Components
- **Architecture**: Decoupled Layered Architecture (MVC / MVP Lite)
- **Data Asset**: Local `questions.json` parsed via standard `org.json` API
- **Minimum SDK**: API 24 (Android 7.0 Nougat)
- **Target / Compile SDK**: API 34
- **Build System**: Gradle 8.5 with AndroidX

---

## 📂 Repository Folder Structure

```
Quiz_Application/
├── README.md
├── Screenshots/
│   └── .gitkeep
└── QuizApp/
    ├── build.gradle (Project)
    ├── settings.gradle
    ├── gradle.properties
    └── app/
        ├── build.gradle (Module)
        └── src/
            ├── test/java/com/aditya/quizapp/
            │   ├── JsonHelperTest.java
            │   └── QuizEngineTest.java
            └── main/
                ├── AndroidManifest.xml
                ├── assets/
                │   └── questions.json
                ├── java/com/aditya/quizapp/
                │   ├── activity/
                │   │   ├── WelcomeActivity.java
                │   │   ├── QuizActivity.java
                │   │   └── ResultActivity.java
                │   ├── constants/AppConstants.java
                │   ├── helper/
                │   │   ├── JsonHelper.java
                │   │   └── QuizEngine.java
                │   └── model/Question.java
                └── res/
                    ├── drawable/
                    │   ├── bg_option_correct.xml
                    │   ├── bg_option_default.xml
                    │   └── bg_option_wrong.xml
                    ├── layout/
                    │   ├── activity_welcome.xml
                    │   ├── activity_quiz.xml
                    │   └── activity_result.xml
                    └── values/
                        ├── colors.xml
                        ├── dimens.xml
                        ├── strings.xml
                        └── styles.xml
```

---

## ⚙️ Installation & Setup Guide

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Aditya11o/OIBSIP-Android.git
   ```
2. **Open in Android Studio**:
   - Open Android Studio.
   - Select **Open an existing project**.
   - Navigate to `OIBSIP-Android/Quiz_Application/QuizApp` and click **OK**.
3. **Build & Run**:
   - Sync project with Gradle files.
   - Run on an Android Emulator or connected physical device (Min SDK 24+).

---

## 🧪 Unit Testing

Run automated JVM unit tests via terminal or Android Studio:
```bash
./gradlew test
```
- `QuizEngineTest`: Validates question shuffling, index progression, score increments, and reset logic.
- `JsonHelperTest`: Validates JSON fallback array construction and data boundary safety.

---

## 📸 Application Screenshots

*(Screenshots will be added in the `Screenshots/` directory upon device deployment)*

---

## 📜 License & Credits

Developed by **Aditya** for the **Oasis InfoByte Internship Program**.  
Released under the [MIT License](../../LICENSE).
