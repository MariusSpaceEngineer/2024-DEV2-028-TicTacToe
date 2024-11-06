package com.katas.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.katas.tictactoe.composables.GameGrid
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
                val winnerMessage = remember { mutableStateOf("") }

                Column {
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
                            val winner = checkWin(boardState.value)
                            if (winner != null) {
                                // Update the winner message
                                winnerMessage.value =
                                    if (winner == SquareState.Cross) "Player 1 wins!" else "Player 2 wins!"
                            } else if (boardState.value.flatten()
                                    .none { it == SquareState.None }
                            ) {
                                // Check for a draw
                                winnerMessage.value = "It's a draw!"
                            } else {
                                // Switch the current player
                                currentPlayer.value =
                                    if (currentPlayer.value == Player.Player1) Player.Player2 else Player.Player1
                            }
                        }
                    })
                    if (winnerMessage.value.isNotEmpty()) {
                        Text(text = winnerMessage.value)
                    }
                }
            }
        }
    }
}
