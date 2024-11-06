package com.katas.tictactoe

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.katas.tictactoe.composables.GameGrid
import com.katas.tictactoe.composables.GameOverDialog
import com.katas.tictactoe.utils.Player
import com.katas.tictactoe.utils.SquareState
import com.katas.tictactoe.utils.checkWin
import org.junit.Rule
import org.junit.Test

class GameGridTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun grid_displaysAllSquares() {
        // Arrange: Initialize the board state with all squares set to None
        val boardState = List(3) { List(3) { SquareState.None } }

        // Act: Set the content to display the GameGrid composable
        composeTestRule.setContent {
            GameGrid(boardState = boardState, onSquareClick = { _, _ -> })
        }

        // Assert: Verify that all squares in the grid are displayed
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                composeTestRule.onNodeWithContentDescription("Square $i-$j")
                    .assertIsDisplayed()
            }
        }
    }

    @Test
    fun gameGrid_displaysCorrectLogo_whenSquareIsClicked() {
        // Arrange: Initialize the board state with all squares set to None
        val boardState = mutableStateOf(List(3) { List(3) { SquareState.None } })
        // Arrange: Initialize the current player
        var currentPlayer = Player.Player1
        // Arrange: Initialize variables to track the clicked row and column
        var clickedRow = -1
        var clickedCol = -1

        composeTestRule.setContent {
            GameGrid(boardState = boardState.value, onSquareClick = { row, col ->
                // Update the clicked row and column when a square is clicked
                clickedRow = row
                clickedCol = col
                // Update the board state and the current player when a square is clicked
                boardState.value = boardState.value.toMutableList().apply {
                    this[row] = this[row].toMutableList().apply {
                        this[col] =
                            if (currentPlayer == Player.Player1) SquareState.Cross else SquareState.Circle
                    }
                }
                currentPlayer =
                    if (currentPlayer == Player.Player1) Player.Player2 else Player.Player1
            })
        }

        // Act: Perform a click on the square at position (1, 1)
        composeTestRule.onNodeWithContentDescription("Square 1-1")
            .performClick()

        // Assert: Verify that the correct square was clicked
        assert(clickedRow == 1)
        assert(clickedCol == 1)
        // Assert: Verify that the correct logo is displayed in the clicked square
        composeTestRule.onNodeWithContentDescription("Cross")
            .assertIsDisplayed()

        // Act: Perform a click on another square at position (1, 2)
        // The currentPlayer should switch to Player2
        composeTestRule.onNodeWithContentDescription("Square 1-2")
            .performClick()

        // Assert: Verify that the correct square was clicked
        assert(clickedRow == 1)
        assert(clickedCol == 2)
        // Assert: Verify that the correct logo is displayed in the new clicked square
        composeTestRule.onNodeWithContentDescription("Circle")
            .assertIsDisplayed()
    }


    @Test
    fun gameGrid_displaysWinnerMessage_whenPlayerWins() {
        // Arrange: Initialize the board state with all squares set to None
        val boardState = mutableStateOf(List(3) { List(3) { SquareState.None } })
        // Arrange: Initialize the current player
        var currentPlayer = Player.Player1
        // Arrange: Initialize the dialog state
        val showDialog = mutableStateOf(false)
        val isDraw = mutableStateOf(false)
        var winner: SquareState? = null

        composeTestRule.setContent {
            GameGrid(boardState = boardState.value, onSquareClick = { row, col ->
                boardState.value = boardState.value.toMutableList().apply {
                    this[row] = this[row].toMutableList().apply {
                        this[col] =
                            if (currentPlayer == Player.Player1) SquareState.Cross else SquareState.Circle
                    }
                }
                winner = checkWin(boardState.value)
                if (winner != null) {
                    showDialog.value = true
                } else if (boardState.value.flatten().none { it == SquareState.None }) {
                    isDraw.value = true
                    showDialog.value = true
                } else {
                    currentPlayer =
                        if (currentPlayer == Player.Player1) Player.Player2 else Player.Player1
                }
            })
            GameOverDialog(
                showDialog = showDialog.value,
                isDraw = isDraw.value,
                winner = winner,
                onPlayAgain = { /* Do nothing */ },
                onCloseGame = { /* Do nothing */ }
            )
        }

        // Simulate a winning move for Player 1
        composeTestRule.onNodeWithContentDescription("Square 0-0").performClick()
        composeTestRule.onNodeWithContentDescription("Square 1-0").performClick()
        composeTestRule.onNodeWithContentDescription("Square 0-1").performClick()
        composeTestRule.onNodeWithContentDescription("Square 1-1").performClick()
        composeTestRule.onNodeWithContentDescription("Square 0-2").performClick()

        // Assert that the GameOverDialog is displayed with the correct message
        composeTestRule.onNodeWithText("Player 1 wins!").assertIsDisplayed()
        // Assert that the GameOverDialog composable is displayed
        composeTestRule.onNodeWithText("Game Over").assertIsDisplayed()
    }

    @Test
    fun gameGrid_displaysWinnerMessage_whenPlayer2Wins() {
        // Arrange: Initialize the board state with all squares set to None
        val boardState = mutableStateOf(List(3) { List(3) { SquareState.None } })
        // Arrange: Initialize the current player
        var currentPlayer = Player.Player1
        // Arrange: Initialize the dialog state
        val showDialog = mutableStateOf(false)
        val isDraw = mutableStateOf(false)
        var winner: SquareState? = null

        composeTestRule.setContent {
            GameGrid(boardState = boardState.value, onSquareClick = { row, col ->
                boardState.value = boardState.value.toMutableList().apply {
                    this[row] = this[row].toMutableList().apply {
                        this[col] =
                            if (currentPlayer == Player.Player1) SquareState.Cross else SquareState.Circle
                    }
                }
                winner = checkWin(boardState.value)
                if (winner != null) {
                    showDialog.value = true
                } else if (boardState.value.flatten().none { it == SquareState.None }) {
                    isDraw.value = true
                    showDialog.value = true
                } else {
                    currentPlayer =
                        if (currentPlayer == Player.Player1) Player.Player2 else Player.Player1
                }
            })
            GameOverDialog(
                showDialog = showDialog.value,
                isDraw = isDraw.value,
                winner = winner,
                onPlayAgain = { /* Do nothing */ },
                onCloseGame = { /* Do nothing */ }
            )
        }

        // Simulate a winning move for Player 2
        composeTestRule.onNodeWithContentDescription("Square 0-0").performClick() // x
        composeTestRule.onNodeWithContentDescription("Square 1-0").performClick() // o
        composeTestRule.onNodeWithContentDescription("Square 0-1").performClick() // x
        composeTestRule.onNodeWithContentDescription("Square 1-1").performClick() // o
        composeTestRule.onNodeWithContentDescription("Square 2-2").performClick() // x
        composeTestRule.onNodeWithContentDescription("Square 1-2").performClick() // o

        // Assert that the GameOverDialog is displayed with the correct message
        composeTestRule.onNodeWithText("Player 2 wins!").assertIsDisplayed()
        // Assert that the GameOverDialog composable is displayed
        composeTestRule.onNodeWithText("Game Over").assertIsDisplayed()
    }

    @Test
    fun gameGrid_displaysDrawMessage_whenGameIsDraw() {
        // Arrange: Initialize the board state with all squares set to None
        val boardState = mutableStateOf(List(3) { List(3) { SquareState.None } })
        // Arrange: Initialize the current player
        var currentPlayer = Player.Player1
        // Arrange: Initialize the dialog state
        val showDialog = mutableStateOf(false)
        val isDraw = mutableStateOf(false)
        var winner: SquareState? = null

        composeTestRule.setContent {
            GameGrid(boardState = boardState.value, onSquareClick = { row, col ->
                boardState.value = boardState.value.toMutableList().apply {
                    this[row] = this[row].toMutableList().apply {
                        this[col] =
                            if (currentPlayer == Player.Player1) SquareState.Cross else SquareState.Circle
                    }
                }
                winner = checkWin(boardState.value)
                if (winner != null) {
                    showDialog.value = true
                } else if (boardState.value.flatten().none { it == SquareState.None }) {
                    isDraw.value = true
                    showDialog.value = true
                } else {
                    currentPlayer =
                        if (currentPlayer == Player.Player1) Player.Player2 else Player.Player1
                }
            })
            GameOverDialog(
                showDialog = showDialog.value,
                isDraw = isDraw.value,
                winner = winner,
                onPlayAgain = { /* Do nothing */ },
                onCloseGame = { /* Do nothing */ }
            )
        }

        // Simulate moves leading to a draw
        composeTestRule.onNodeWithContentDescription("Square 0-0").performClick() // x
        composeTestRule.onNodeWithContentDescription("Square 0-1").performClick() // o
        composeTestRule.onNodeWithContentDescription("Square 0-2").performClick() // x
        composeTestRule.onNodeWithContentDescription("Square 1-1").performClick() // o
        composeTestRule.onNodeWithContentDescription("Square 1-0").performClick() // x
        composeTestRule.onNodeWithContentDescription("Square 1-2").performClick() // o
        composeTestRule.onNodeWithContentDescription("Square 2-1").performClick() // x
        composeTestRule.onNodeWithContentDescription("Square 2-0").performClick() // o
        composeTestRule.onNodeWithContentDescription("Square 2-2").performClick() // x

        // Assert: Verify that the draw message is displayed
        composeTestRule.onNodeWithText("It's a draw!").assertIsDisplayed()
        // Assert: Verify that the GameOverDialog composable is displayed
        composeTestRule.onNodeWithText("Game Over").assertIsDisplayed()
    }
}