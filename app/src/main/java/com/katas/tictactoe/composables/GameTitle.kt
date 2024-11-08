package com.katas.tictactoe.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.katas.tictactoe.ui.theme.TextColor

/**
 * Composable function that displays the title of the game.
 *
 * @param title The title text to be displayed.
 */
@Composable
fun GameTitle(title: String) {
    Text(
        text = title,
        fontSize = 48.sp,
        color = TextColor,
        modifier = Modifier.padding(16.dp)
    )
}