package com.katas.tictactoe

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.katas.tictactoe.composables.GameSquare
import com.katas.tictactoe.utils.SquareState
import org.junit.Rule
import org.junit.Test


class GameSquareTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun square_isEmpty_whenStateIsNone() {
        // Arrange
        composeTestRule.setContent {
            GameSquare(state = SquareState.None, onClick = {}, contentDescription = "Square")
        }

        // Assert
        composeTestRule.onNodeWithContentDescription("Cross")
            .assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("Circle")
            .assertDoesNotExist()
    }

    @Test
    fun square_displaysCross_whenStateIsCross() {
        // Arrange
        composeTestRule.setContent {
            GameSquare(state = SquareState.Cross, onClick = {}, contentDescription = "Square")
        }

        // Assert
        composeTestRule.onNodeWithContentDescription("Cross")
            .assertIsDisplayed()
    }

    @Test
    fun square_displaysCircle_whenStateIsCircle() {
        // Arrange
        composeTestRule.setContent {
            GameSquare(state = SquareState.Circle, onClick = {}, contentDescription = "Square")
        }

        // Assert
        composeTestRule.onNodeWithContentDescription("Circle")
            .assertIsDisplayed()
    }

    @Test
    fun square_triggersOnClick_whenClicked() {
        // Arrange
        var clicked = false

        // Act
        composeTestRule.setContent {
            GameSquare(state = SquareState.None, onClick = { clicked = true }, contentDescription = "Square")
        }
        composeTestRule.onNodeWithContentDescription("Square")
            .performClick()

        // Assert
        assert(clicked)
    }
}