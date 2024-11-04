package com.katas.tictactoe.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.katas.tictactoe.R
import com.katas.tictactoe.ui.theme.CircleColor
import com.katas.tictactoe.ui.theme.CrossColor
import com.katas.tictactoe.ui.theme.GameSquareBackgroundColor
import com.katas.tictactoe.utils.SquareState

@Composable
fun GameSquare(
    state: SquareState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .size(100.dp)
            .background(GameSquareBackgroundColor)
            .clickable { onClick() }
            .semantics { contentDescription = "Square" },
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            SquareState.None -> {}
            SquareState.Cross -> Icon(
                painter = painterResource(id = R.drawable.ic_cross),
                contentDescription = "Cross",
                modifier = Modifier.size(80.dp),
                tint = CrossColor
            )

            SquareState.Circle -> Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = "Circle",
                modifier = Modifier.size(80.dp),
                tint = CircleColor
            )
        }
    }
}