package com.katas.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.katas.tictactoe.composables.GameGrid
import com.katas.tictactoe.ui.theme.TicTacToeTheme
import com.katas.tictactoe.utils.Player
import com.katas.tictactoe.utils.SquareState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                val boardState =
                    remember { mutableStateOf(MutableList(3) { MutableList(3) { SquareState.None } }) }
                val currentPlayer = remember { mutableStateOf(Player.Player1) }

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
                        // Switch the current player
                        currentPlayer.value =
                            if (currentPlayer.value == Player.Player1) Player.Player2 else Player.Player1
                    }
                })
            }
        }
    }
}