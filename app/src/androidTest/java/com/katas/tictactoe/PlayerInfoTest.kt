package com.katas.tictactoe

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import com.katas.tictactoe.composables.PlayerInfo
import com.katas.tictactoe.ui.theme.CurrentPlayerNameBackgroundColor
import com.katas.tictactoe.utils.Player
import org.junit.Rule
import org.junit.Test
import org.junit.Assert

class PlayerInfoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun playerInfo_displaysCorrectPlayerInfo() {
        // Arrange: Initialize the players
        val player = Player.Player1
        val currentPlayer = Player.Player1

        // Act: Set the content to the PlayerInfo composable
        composeTestRule.setContent {
            PlayerInfo(player = player, currentPlayer = currentPlayer)
        }

        // Assert: Verify that the player information is displayed correctly
        composeTestRule.onNodeWithText("Player 1").assertIsDisplayed()
    }

    //Minimum API level for this test is 28
    @Test
    fun playerInfo_highlightsCurrentPlayer() {
        // Arrange: Initialize the players
        val player = Player.Player1
        val currentPlayer = Player.Player1

        // Act: Set the content to the PlayerInfo composable
        composeTestRule.setContent {
            PlayerInfo(player = player, currentPlayer = currentPlayer)
        }


        // Assert: Verify that the current player's text is highlighted
        composeTestRule.onNodeWithText("Player 1")
            .assertBackgroundColor(CurrentPlayerNameBackgroundColor)

    }

    /**
     * Asserts that the background color of the node matches the expected color.
     *
     * Note: Minimum API level for this function is 28.
     *
     * @param expectedBackground The expected background color.
     */
    private fun SemanticsNodeInteraction.assertBackgroundColor(expectedBackground: Color) {
        val array = IntArray(20)
        captureToImage()
            .readPixels(
                array,
                startY = 0, // Adjusted startY to be within bounds
                startX = 0, // Adjusted startX to be within bounds
                width = 5,
                height = 4
            )
        array.forEach {
            Assert.assertEquals(expectedBackground.convert(ColorSpaces.Srgb).hashCode(), it)
        }
    }
}