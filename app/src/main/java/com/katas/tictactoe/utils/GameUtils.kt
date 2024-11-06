package com.katas.tictactoe.utils

enum class SquareState {
    None,
    Cross,
    Circle
}

enum class Player {
    Player1,
    Player2
}

/**
 * Checks if there is a winner or a draw in the current board state.
 * @return The winning [SquareState] or null if there is no winner.
 */
fun checkWin(boardState: List<List<SquareState>>): SquareState? {
    // Check each row for a win
    for (i in 0 until 3) {
        if (boardState[i][0] != SquareState.None && boardState[i][0] == boardState[i][1] && boardState[i][1] == boardState[i][2]) {
            return boardState[i][0]
        }
    }

    // Check each column for a win
    for (i in 0 until 3) {
        if (boardState[0][i] != SquareState.None && boardState[0][i] == boardState[1][i] && boardState[1][i] == boardState[2][i]) {
            return boardState[0][i]
        }
    }

    // Check the first diagonal (top-left to bottom-right) for a win
    if (boardState[0][0] != SquareState.None && boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2]) {
        return boardState[0][0]
    }

    // Check the second diagonal (top-right to bottom-left) for a win
    if (boardState[0][2] != SquareState.None && boardState[0][2] == boardState[1][1] && boardState[1][1] == boardState[2][0]) {
        return boardState[0][2]
    }

    // No winner
    return null
}

