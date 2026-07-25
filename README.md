# AICTE Oasis Infobyte Internship Program (OIBSIP) — Android Development

This repository contains the complete portfolio of Android applications developed by **Aditya Halder** for the **AICTE Oasis Infobyte Internship Program (OIBSIP)** under the domain of **Android Development**.

---

## 📌 Internship Information

- **Intern Candidate**: Aditya Halder
- **Email**: halderaditya632@gmail.com
- **Program**: AICTE Oasis Infobyte Internship Program (OIBSIP)
- **Domain**: Android Development
- **Commencement Date**: 5 July 2026
- **Submission Deadline**: 15 August 2026
- **Organization**: Oasis Infobyte (Satya Niketan, New Delhi, India)

---

## 📱 Portfolio Projects Overview

All projects are offline-first, native Android applications built using **Java 8**, **Material Design 3 Components**, and modern software architecture principles (Decoupled Layered Architecture / MVC / MVP Lite).

| Task # | Project Name | Description | Key Tech / Components | Status |
| :---: | :--- | :--- | :--- | :---: |
| **Task 1** | **[Unit Converter](file:///d:/Desktop/OIBSIP-Android/Unit_Converter/)** | Multi-category unit conversion tool (Length, Weight, Temp) with base-unit normalization engine and strict validation. | Java, XML, Material Design 3, DecimalFormat, JUnit | ✔ Completed |
| **Task 2** | **[Quiz Application](file:///d:/Desktop/OIBSIP-Android/Quiz_Application/)** | Offline multiple-choice quiz app with dynamic JSON asset loading, shuffling, instant color feedback, and performance badges. | Java, XML, Material 3, JSON Assets, RadioGroup, JUnit | ✔ Completed |
| **Task 3** | **[Stopwatch Application](file:///d:/Desktop/OIBSIP-Android/Stopwatch/)** | High-precision stopwatch with non-drifting `SystemClock` deltas, `StopwatchState` finite state machine, and RecyclerView lap history. | Java, XML, Material 3, Handler/Runnable, RecyclerView, JUnit | ✔ Completed |

---

## 📂 Repository Structure

```
OIBSIP-Android/
├── README.md
├── LICENSE
│
├── Unit_Converter/
│   ├── README.md
│   ├── Screenshots/
│   └── UnitConverterApp/
│       └── app/src/main/java/com/aditya/unitconverter/
│
├── Quiz_Application/
│   ├── README.md
│   ├── Screenshots/
│   └── QuizApp/
│       └── app/src/main/
│           ├── assets/questions.json
│           └── java/com/aditya/quizapp/
│               ├── activity/ (WelcomeActivity, QuizActivity, ResultActivity)
│               ├── constants/ (AppConstants)
│               ├── helper/ (JsonHelper, QuizEngine)
│               └── model/ (Question)
│
└── Stopwatch/
    ├── README.md
    ├── Screenshots/
    └── StopwatchApp/
        └── app/src/main/java/com/aditya/stopwatch/
            ├── activity/ (MainActivity)
            ├── adapter/ (LapAdapter)
            ├── constants/ (AppConstants, StopwatchState)
            ├── engine/ (StopwatchEngine)
            ├── model/ (Lap)
            └── utils/ (TimeFormatter)
```

---

## 🛠 Technology Stack & Development Standards

- **Core Language**: Java 8
- **UI Framework**: XML Layouts with Material Design 3 Components (`MaterialToolbar`, `MaterialCardView`, `MaterialButton`, `MaterialTextView`, `RecyclerView`, `MaterialDivider`)
- **Min SDK**: API 24 (Android 7.0 Nougat)
- **Target SDK**: API 34
- **Build Tooling**: Gradle 8.5 with Android Gradle Plugin (AGP) 8.2.2
- **Testing**: Automated JVM JUnit Unit Tests across all modules
- **Architecture**: Decoupled Layered Architecture with strict separation of UI controllers, business logic engines, data models, and formatters.

---

## 🚀 Projects Detailed Summary

### 1. Unit Converter Application (`Unit_Converter/`)
- Real-time conversion across 3 major measurement categories: **Length** (mm, cm, m, km, inch, foot, yard, mile), **Weight** (mg, g, kg, metric ton, ounce, pound), and **Temperature** (Celsius, Fahrenheit, Kelvin).
- **Base-Unit Normalization Engine**: Converts inputs to base units (meters/grams) to maintain $O(N)$ lookup maps instead of $O(N^2)$ direct formulas.
- **Material Design 3 UI**: Floating elevated cards, circular Swap (⇄) button, and dynamic adapter updates.
- **Validation Matrix**: Guards against empty inputs, non-numeric strings, negative length/weight values, sub-absolute-zero temperatures, and numeric overflow ($>10^{12}$).

### 2. Quiz Application (`Quiz_Application/`)
- Offline multiple-choice quiz experience with 10 tech/Android trivia questions stored in `assets/questions.json`.
- **Dynamic Shuffling Engine**: Shuffles questions automatically (`Collections.shuffle()`) on launch/reset.
- **Visual Feedback System**: Highlights correct answers in **Green** (`#4CAF50`) and incorrect choices in **Red** (`#F44336`), disabling option radio buttons upon click.
- **Results & Performance Badges**: Displays total score, accuracy percentage, correct/wrong counts, and performance badges (🏆 Quiz Master!, 👍 Good Job!, 📚 Keep Practicing!).

### 3. Stopwatch Application (`Stopwatch/`)
- Prominent 48sp time display formatted as `MM:SS.SS` or `HH:MM:SS.SS` (`00:00.00`).
- **Non-Drifting SystemClock Delta Engine**: Uses `SystemClock.elapsedRealtime()` deltas (`elapsedTime = SystemClock.elapsedRealtime() - startTime`), guaranteeing zero timing drift across OS thread latencies or backgrounding.
- **`StopwatchState` Finite State Machine**: Controls `RESET`, `RUNNING`, and `PAUSED` state transitions and button enable/disable flags.
- **RecyclerView Lap System**: Records lap splits in reverse chronological order (newest lap at top) with smooth auto-scrolling and empty state handling.

---

## ⚙️ Building & Running Projects

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Aditya11o/OIBSIP-Android.git
   ```
2. **Open a Project in Android Studio**:
   - Launch Android Studio.
   - Select **Open an existing project**.
   - Navigate to any module subfolder:
     - `OIBSIP-Android/Unit_Converter/UnitConverterApp`
     - `OIBSIP-Android/Quiz_Application/QuizApp`
     - `OIBSIP-Android/Stopwatch/StopwatchApp`
3. **Build & Execute**:
   - Sync project with Gradle files.
   - Run on an Android Emulator or connected physical device (Min SDK 24+).

---

## 🧪 Unit Testing

Run automated JUnit tests for any project module via terminal:
```bash
# Example: Run unit tests for Stopwatch project
cd Stopwatch/StopwatchApp
./gradlew test
```

---

## ⚖️ License

Developed by **Aditya Halder** for the **AICTE Oasis Infobyte Internship Program**.  
Licensed under the [MIT License](LICENSE).
