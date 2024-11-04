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
    fun gameSquare_isEmpty_whenStateIsNone() {
        // Arrange
        composeTestRule.setContent {
            GameSquare(state = SquareState.None, onClick = {})
        }

        // Assert
        composeTestRule.onNodeWithContentDescription("Cross")
            .assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("Circle")
            .assertDoesNotExist()
    }

    @Test
    fun gameSquare_displaysCross_whenStateIsCross() {
        // Arrange
        composeTestRule.setContent {
            GameSquare(state = SquareState.Cross, onClick = {})
        }

        // Assert
        composeTestRule.onNodeWithContentDescription("Cross")
            .assertIsDisplayed()
    }

    @Test
    fun gameSquare_displaysCircle_whenStateIsCircle() {
        // Arrange
        composeTestRule.setContent {
            GameSquare(state = SquareState.Circle, onClick = {})
        }

        // Assert
        composeTestRule.onNodeWithContentDescription("Circle")
            .assertIsDisplayed()
    }

    @Test
    fun gameSquare_triggersOnClick_whenClicked() {
        // Arrange
        var clicked = false

        // Act
        composeTestRule.setContent {
            GameSquare(state = SquareState.None, onClick = { clicked = true })
        }
        composeTestRule.onNodeWithContentDescription("Square")
            .performClick()

        // Assert
        assert(clicked)
    }
}