package com.katas.tictactoe.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.katas.tictactoe.ui.theme.GameGridBackgroundColor
import com.katas.tictactoe.utils.SquareState

/**
 * Composable function that represents the grid of the game.
 * The grid displays the current state of the board and handles square clicks.
 */
@Composable
fun GameGrid(boardState: List<List<SquareState>>, onSquareClick: (Int, Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(GameGridBackgroundColor)
            .padding(10.dp)
    ) {
        for (i in 0 until 3) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (j in 0 until 3) {
                    GameSquare(
                        state = boardState[i][j],
                        onClick = { onSquareClick(i, j) },
                        modifier = Modifier.padding(4.dp),
                        contentDescription = "Square $i-$j"
                    )
                }
            }
        }
    }
}