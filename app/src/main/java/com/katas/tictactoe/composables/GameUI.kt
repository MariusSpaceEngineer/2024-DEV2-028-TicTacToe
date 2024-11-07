package com.katas.tictactoe.composables

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.katas.tictactoe.R
import com.katas.tictactoe.ui.theme.BackgroundFirstColor
import com.katas.tictactoe.ui.theme.BackgroundSecondColor
import com.katas.tictactoe.utils.Player
import com.katas.tictactoe.utils.SquareState
import com.katas.tictactoe.utils.checkWin

@Composable
fun GameUI(activity: ComponentActivity) {
    // Initialize the game state
    val boardState = remember { mutableStateOf(List(3) { List(3) { SquareState.None } }) }
    val currentPlayer = remember { mutableStateOf(Player.Player1) }
    val showDialog = remember { mutableStateOf(false) }
    val isDraw = remember { mutableStateOf(false) }
    val winner = remember { mutableStateOf<SquareState?>(null) }
    var player1Score by remember { mutableIntStateOf(0) }
    var player2Score by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    0.0f to BackgroundFirstColor,
                    0.54f to BackgroundSecondColor,
                    center = with(LocalDensity.current) {
                        Offset(
                            x = LocalConfiguration.current.screenWidthDp.dp.toPx() / 2.0f,
                            y = LocalConfiguration.current.screenHeightDp.dp.toPx() / 2.0f
                        )
                    },
                    radius = with(LocalDensity.current) {
                        LocalConfiguration.current.screenWidthDp.dp.toPx() / 2.0f
                    },
                    tileMode = TileMode.Clamp
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_stars),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterStart)
                .offset(x = (-2).dp, y = (-2).dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameTitle("TicTacToe")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                PlayerInfo(
                    player = Player.Player1,
                    currentPlayer = currentPlayer.value
                )
                PlayerInfo(
                    player = Player.Player2,
                    currentPlayer = currentPlayer.value
                )
            }
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
                    activity.finish()
                }
            )
        }
    }
}