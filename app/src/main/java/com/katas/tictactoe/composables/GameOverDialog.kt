package com.katas.tictactoe.composables

import androidx.activity.ComponentActivity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.katas.tictactoe.ui.theme.AlertCancelButtonColor
import com.katas.tictactoe.ui.theme.AlertSubmitButtonColor
import com.katas.tictactoe.utils.SquareState

/**
 * Composable function that displays a game over dialog.
 *
 * @param showDialog Boolean flag to control the visibility of the dialog.
 * @param isDraw Boolean flag indicating if the game ended in a draw.
 * @param winner The state of the winning combination (Cross or Circle), or null if it's a draw.
 * @param onPlayAgain Callback to be invoked when the "Play Again" button is clicked.
 * @param onCloseGame Callback to be invoked when the "Close Game" button is clicked.
 */
@Composable
fun GameOverDialog(
    showDialog: Boolean,
    isDraw: Boolean,
    winner: SquareState?,
    onPlayAgain: () -> Unit,
    onCloseGame: () -> Unit
) {
    if (showDialog) {
        val context = LocalContext.current
        AlertDialog(
            onDismissRequest = { /* Do nothing */ },
            title = { Text(text = "Game Over") },
            text = { Text(text = if (isDraw) "It's a draw!" else if (winner == SquareState.Cross) "Player 1 wins!" else "Player 2 wins!") },
            confirmButton = {
                Button(
                    onClick = {
                        onPlayAgain()
                    },
                    colors = ButtonDefaults.buttonColors(AlertSubmitButtonColor)
                ) {
                    Text("Play Again")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onCloseGame()
                        (context as ComponentActivity).finish()
                    },
                    colors = ButtonDefaults.buttonColors(AlertCancelButtonColor)
                ) {
                    Text("Close Game")
                }
            }
        )
    }
}