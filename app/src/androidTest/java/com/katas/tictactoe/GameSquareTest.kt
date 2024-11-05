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
        // Arrange: Set the content to display a GameSquare with state None
        composeTestRule.setContent {
            GameSquare(state = SquareState.None, onClick = {}, contentDescription = "Square")
        }

        // Assert: Verify that neither Cross nor Circle is displayed
        composeTestRule.onNodeWithContentDescription("Cross")
            .assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("Circle")
            .assertDoesNotExist()
    }

    @Test
    fun square_displaysCross_whenStateIsCross() {
        // Arrange: Set the content to display a GameSquare with state Cross
        composeTestRule.setContent {
            GameSquare(state = SquareState.Cross, onClick = {}, contentDescription = "Square")
        }

        // Assert: Verify that Cross is displayed
        composeTestRule.onNodeWithContentDescription("Cross")
            .assertIsDisplayed()
    }

    @Test
    fun square_displaysCircle_whenStateIsCircle() {
        // Arrange: Set the content to display a GameSquare with state Circle
        composeTestRule.setContent {
            GameSquare(state = SquareState.Circle, onClick = {}, contentDescription = "Square")
        }

        // Assert: Verify that Circle is displayed
        composeTestRule.onNodeWithContentDescription("Circle")
            .assertIsDisplayed()
    }

    @Test
    fun square_triggersOnClick_whenClicked() {
        // Arrange: Initialize a variable to track click state
        var clicked = false

        // Act: Set the content to display a GameSquare and update the click state when clicked
        composeTestRule.setContent {
            GameSquare(state = SquareState.None, onClick = { clicked = true }, contentDescription = "Square")
        }
        composeTestRule.onNodeWithContentDescription("Square")
            .performClick()

        // Assert: Verify that the click state was updated
        assert(clicked)
    }
}