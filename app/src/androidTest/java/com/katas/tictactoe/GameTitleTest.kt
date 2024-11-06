package com.katas.tictactoe

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.katas.tictactoe.composables.GameTitle
import org.junit.Rule
import org.junit.Test

class GameTitleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun gameTitle_displaysCorrectTitle() {
        // Arrange: Initialize the title text
        val titleText = "TicTacToe"

        // Act: Set the content to the GameTitle composable
        composeTestRule.setContent {
            GameTitle(title = titleText)
        }

        // Assert: Verify that the title is displayed correctly
        composeTestRule.onNodeWithText(titleText).assertIsDisplayed()
    }
}