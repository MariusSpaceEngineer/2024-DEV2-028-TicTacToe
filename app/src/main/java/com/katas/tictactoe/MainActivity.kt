package com.katas.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.katas.tictactoe.composables.GameGrid
import com.katas.tictactoe.ui.theme.TicTacToeTheme
import com.katas.tictactoe.utils.SquareState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                val boardState = List(3) { List(3) { SquareState.None } }
                GameGrid(boardState = boardState, onSquareClick = { _, _ ->
                    // Handle square click
                })
            }
        }
    }
}