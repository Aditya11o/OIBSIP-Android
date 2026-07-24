# Stopwatch Application

A high-precision, offline-first Android stopwatch application built with **Java**, **Material Design 3 Components**, a non-drifting **`SystemClock` Delta Engine**, and a **`StopwatchState` Finite State Machine**. Developed as Task 3 for the **Oasis InfoByte Internship Program (OIBSIP)** under the `OIBSIP-Android` repository.

---

## 📱 Features

- ✔ **Large Stopwatch Display**: Prominent time display formatted as `MM:SS.SS` or `HH:MM:SS.SS` (e.g. `00:00.00`) using 48sp typography.
- ✔ **Start / Resume Button**: Starts timer from zero or resumes counting from a paused state.
- ✔ **Pause Button**: Freezes counting instantly without resetting accumulated time.
- ✔ **Reset Button**: Stops timer, resets display to `00:00.00`, and clears lap history.
- ✔ **StopwatchState Finite State Machine**:
  - **`RESET`**: Start Enabled, Pause Disabled, Reset Disabled, Lap Disabled.
  - **`RUNNING`**: Start Disabled, Pause Enabled, Reset Enabled, Lap Enabled.
  - **`PAUSED`**: Start Enabled (Resume), Pause Disabled, Reset Enabled, Lap Disabled.
- ✔ **Reverse Chronological Lap History**:
  - Records lap number, lap split time, and total timestamp into a scrollable `RecyclerView`.
  - Automatically inserts new laps at top (Index 0) and smooth auto-scrolls.
  - Automatically toggles empty state text (`"No laps recorded yet"`).
- ✔ **Lifecycle & Background Integrity**:
  - Uses `SystemClock.elapsedRealtime()` base clock deltas (`elapsedTime = SystemClock.elapsedRealtime() - startTime`), eliminating timer drift.
  - Keeps accurate time even when app is backgrounded or when device sleeps.
  - Full orientation state preservation (`onSaveInstanceState`).

---

## 🛠 Tech Stack & Specifications

- **Language**: Java 8
- **UI Framework**: XML Layouts with Material Design 3 Components (`MaterialToolbar`, `MaterialCardView`, `MaterialButton`, `MaterialTextView`, `RecyclerView`, `MaterialDivider`)
- **Concurrency & Timing**: `Handler`, `Runnable`, `SystemClock.elapsedRealtime()`
- **Architecture**: Decoupled Layered Architecture (MVC / MVP Lite)
- **Minimum SDK**: API 24 (Android 7.0 Nougat)
- **Target / Compile SDK**: API 34
- **Build System**: Gradle 8.5 with AndroidX

---

## 📂 Repository Folder Structure

```
Stopwatch/
├── README.md
├── Screenshots/
│   └── .gitkeep
└── StopwatchApp/
    ├── build.gradle (Project)
    ├── settings.gradle
    ├── gradle.properties
    └── app/
        ├── build.gradle (Module)
        └── src/
            ├── test/java/com/aditya/stopwatch/
            │   ├── StopwatchEngineTest.java
            │   └── TimeFormatterTest.java
            └── main/
                ├── AndroidManifest.xml
                ├── java/com/aditya/stopwatch/
                │   ├── activity/MainActivity.java
                │   ├── adapter/LapAdapter.java
                │   ├── constants/
                │   │   ├── AppConstants.java
                │   │   └── StopwatchState.java
                │   ├── engine/StopwatchEngine.java
                │   ├── model/Lap.java
                │   └── utils/TimeFormatter.java
                └── res/
                    ├── drawable/bg_timer_card.xml
                    ├── layout/
                    │   ├── activity_main.xml
                    │   └── item_lap.xml
                    └── values/
                        ├── colors.xml
                        ├── dimens.xml
                        ├── strings.xml
                        └── styles.xml
```

---

## ⚙️ SystemClock Delta Engine Mechanics

To prevent timer drift caused by OS thread scheduling delays:

$$\text{startTime} = \text{SystemClock.elapsedRealtime()} - \text{accumulatedTime}$$
$$\text{elapsedTime} = \text{SystemClock.elapsedRealtime()} - \text{startTime}$$

- A `Handler` bound to `Looper.getMainLooper()` posts 30ms tick callbacks.
- `onPause()` pauses Handler callbacks to save battery while `SystemClock` continues tracking real-world time.
- `onResume()` calculates exact elapsed time seamlessly.

---

## ⚙️ Installation & Setup Guide

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Aditya11o/OIBSIP-Android.git
   ```
2. **Open in Android Studio**:
   - Open Android Studio.
   - Select **Open an existing project**.
   - Navigate to `OIBSIP-Android/Stopwatch/StopwatchApp` and click **OK**.
3. **Build & Run**:
   - Sync project with Gradle files.
   - Run on an Android Emulator or physical device (Min SDK 24+).

---

## 🧪 Unit Testing

Run automated JVM unit tests via terminal or Android Studio:
```bash
./gradlew test
```
- `StopwatchEngineTest`: Validates timer state changes, start, pause time freezing, and engine resets.
- `TimeFormatterTest`: Validates zero millisecond formatting, sub-minute, sub-hour, multi-hour, and negative input protections.

---

## 📸 Application Screenshots

*(Screenshots will be added in the `Screenshots/` directory upon device deployment)*

---

## 📜 License & Credits

Developed by **Aditya** for the **Oasis InfoByte Internship Program**.  
Released under the [MIT License](../../LICENSE).
