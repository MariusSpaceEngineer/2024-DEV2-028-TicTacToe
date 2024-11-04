package com.katas.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.katas.tictactoe.composables.GameSquare
import com.katas.tictactoe.ui.theme.TicTacToeTheme
import com.katas.tictactoe.utils.SquareState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
              GameSquare(state = SquareState.None, onClick = {})
            }
        }
    }
}