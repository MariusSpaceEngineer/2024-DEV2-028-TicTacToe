package com.katas.tictactoe.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.katas.tictactoe.ui.theme.TextColor

/**
 * Composable function that displays the current score of the game session.
 */
@Composable
fun GameScore(player1Score: Int, player2Score: Int) {
    Text(
        text = "$player1Score - $player2Score",
        modifier = Modifier.padding(16.dp),
        fontSize = 64.sp,
        color = TextColor
    )
}