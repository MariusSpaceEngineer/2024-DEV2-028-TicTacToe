package com.katas.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.katas.tictactoe.composables.GameGrid
import com.katas.tictactoe.composables.GameOverDialog
import com.katas.tictactoe.composables.GameScore
import com.katas.tictactoe.ui.theme.TicTacToeTheme
import com.katas.tictactoe.utils.Player
import com.katas.tictactoe.utils.SquareState
import com.katas.tictactoe.utils.checkWin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                // Initialize the game state
                val boardState =
                    remember { mutableStateOf(List(3) { List(3) { SquareState.None } }) }
                val currentPlayer = remember { mutableStateOf(Player.Player1) }
                val showDialog = remember { mutableStateOf(false) }
                val isDraw = remember { mutableStateOf(false) }
                val winner = remember { mutableStateOf<SquareState?>(null) }
                var player1Score by remember { mutableIntStateOf(0) }
                var player2Score by remember { mutableIntStateOf(0) }

                Column {
                    GameScore(player1Score = player1Score, player2Score = player2Score)
                    GameGrid(boardState = boardState.value, onSquareClick = { i, j ->
                        // Check if the selected square is empty and the game is not over
                        if (boardState.value[i][j] == SquareState.None) {
                            // Update the board state with the current player's move
                            boardState.value = boardState.value.toMutableList().apply {
                                this[i] = this[i].toMutableList().apply {
                                    this[j] =
                                        if (currentPlayer.value == Player.Player1) SquareState.Cross else SquareState.Circle
                                }
                            }
                            // Check for a winner
                            winner.value = checkWin(boardState.value)
                            if (winner.value != null) {
                                showDialog.value = true
                                if (winner.value == SquareState.Cross) {
                                    player1Score++
                                } else {
                                    player2Score++
                                }
                            } else if (boardState.value.flatten().none { it == SquareState.None }) {
                                // Check for a draw
                                isDraw.value = true
                                showDialog.value = true
                            } else {
                                // Switch the current player
                                currentPlayer.value =
                                    if (currentPlayer.value == Player.Player1) Player.Player2 else Player.Player1
                            }
                        }
                    })
                    GameOverDialog(
                        showDialog = showDialog.value,
                        isDraw = isDraw.value,
                        winner = winner.value,
                        onPlayAgain = {
                            // Reset the game state
                            boardState.value = List(3) { List(3) { SquareState.None } }
                            currentPlayer.value = Player.Player1
                            showDialog.value = false
                            isDraw.value = false
                            winner.value = null
                        },
                        onCloseGame = {
                            // Close the game
                            finish()
                        }
                    )
                }
            }
        }
    }
}


