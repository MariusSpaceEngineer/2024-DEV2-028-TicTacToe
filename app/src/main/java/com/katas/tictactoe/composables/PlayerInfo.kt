package com.katas.tictactoe.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.katas.tictactoe.R
import com.katas.tictactoe.ui.theme.CircleColor
import com.katas.tictactoe.ui.theme.CrossColor
import com.katas.tictactoe.ui.theme.CurrentPlayerNameBackgroundColor
import com.katas.tictactoe.ui.theme.CurrentPlayerTextColor
import com.katas.tictactoe.ui.theme.DefaultPlayerNameBackgroundColor
import com.katas.tictactoe.ui.theme.TextColor
import com.katas.tictactoe.utils.Player

/**
 * Composable function that displays information about a player in the game.
 *
 * @param player The player whose information is being displayed (Player 1 or Player 2).
 * @param currentPlayer The player who is currently taking their turn.
 */
@Composable
fun PlayerInfo(player: Player, currentPlayer: Player) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = if (player == Player.Player1) R.drawable.ic_cross else R.drawable.ic_circle),
            contentDescription = null,
            tint = if (player == Player.Player1) CrossColor else CircleColor,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = if (player == Player.Player1) "Player 1" else "Player 2",
            fontSize = 28.sp,
            color = if (currentPlayer == player) CurrentPlayerTextColor else TextColor,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(if (currentPlayer == player) CurrentPlayerNameBackgroundColor else DefaultPlayerNameBackgroundColor)
                .padding(6.dp)
        )
    }
}