package com.katas.tictactoe

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.katas.tictactoe.composables.GameScore
import org.junit.Rule
import org.junit.Test

class GameScoreTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun gameScore_displaysCorrectScore() {
        // Arrange: Initialize the scores for Player 1 and Player 2
        val player1Score = 3
        val player2Score = 2

        // Act: Set the content to the GameScore composable
        composeTestRule.setContent {
            GameScore(player1Score = player1Score, player2Score = player2Score)
        }

        // Assert: Verify that the score is displayed correctly
        composeTestRule.onNodeWithText("3 - 2").assertIsDisplayed()
    }
}