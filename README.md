# TicTacToe

This is a simple TicTacToe game implemented using Jetpack Compose in Kotlin. Created using Test Driven Development (TDD)

## Prerequisites
- Android Studio Ladybug | 2024.2.1 Patch 2 or later
- Android SDK with API level 21 or higher (note: for some tests API level 28 or higher is required)
- Gradle 7.0 or later

## Getting Started
### Build the Project
In Android Studio, click on Build -> Make Project or press Ctrl+F9.
Wait for the build to complete.
Run the Project
Connect an Android device via USB or start an Android emulator.
In Android Studio, click on Run -> Run 'app' or press Shift+F10.
Select the target device and click OK.

### Running Tests
To run the tests, follow these steps:  
In Android Studio, open the Project view.
Navigate to app/src/test/java/com/katas/tictactoe for unit tests and app/src/androidTest/java/com/katas/tictactoe for UI tests.
Right-click on the test class you want to run and select Run 'TestClassName'

## Project Structure
app/src/main/java/com/katas/tictactoe: Main application code.
app/src/main/java/com/katas/tictactoe/composables: Composable functions for the UI.
app/src/main/java/com/katas/tictactoe/utils: Utility functions and classes.
app/src/test/java/com/katas/tictactoe: Unit tests.
app/src/androidTest/java/com/katas/tictactoe: UI tests.