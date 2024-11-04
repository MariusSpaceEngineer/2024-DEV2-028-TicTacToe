package com.katas.tictactoe

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.katas.tictactoe.composables.GameGrid
import com.katas.tictactoe.utils.SquareState
import org.junit.Rule
import org.junit.Test

class GameGridTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun grid_displaysAllSquares() {
        // Arrange
        val boardState = List(3) { List(3) { SquareState.None } }

        // Act
        composeTestRule.setContent {
            GameGrid(boardState = boardState, onSquareClick = { _, _ -> })
        }

        // Assert
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                composeTestRule.onNodeWithContentDescription("Square $i-$j")
                    .assertIsDisplayed()
            }
        }
    }

    @Test
    fun grid_triggersOnSquareClick_whenSquareIsClicked() {
        // Arrange
        val boardState = List(3) { List(3) { SquareState.None } }
        var clickedRow = -1
        var clickedCol = -1

        // Act
        composeTestRule.setContent {
            GameGrid(boardState = boardState, onSquareClick = { row, col ->
                clickedRow = row
                clickedCol = col
            })
        }

        composeTestRule.onNodeWithContentDescription("Square 1-1")
            .performClick()

        // Assert
        assert(clickedRow == 1)
        assert(clickedCol == 1)
    }
}