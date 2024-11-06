package com.katas.tictactoe.utils

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class GameUtilsTest {

    @Test
    fun playerEnum_hasCorrectValues() {
        // Assert
        assertEquals(Player.Player1, Player.valueOf("Player1"))
        assertEquals(Player.Player2, Player.valueOf("Player2"))
    }

    @Test
    fun checkWin_identifiesRowWin() {
        // Arrange
        val boardState = listOf(
            listOf(SquareState.Cross, SquareState.Cross, SquareState.Cross),
            listOf(SquareState.None, SquareState.None, SquareState.None),
            listOf(SquareState.None, SquareState.None, SquareState.None)
        )
        // Act & Assert
        assertEquals(SquareState.Cross, checkWin(boardState))
    }

    @Test
    fun checkWin_identifiesColumnWin() {
        // Arrange
        val boardState = listOf(
            listOf(SquareState.Circle, SquareState.None, SquareState.None),
            listOf(SquareState.Circle, SquareState.None, SquareState.None),
            listOf(SquareState.Circle, SquareState.None, SquareState.None)
        )
        // Act & Assert
        assertEquals(SquareState.Circle, checkWin(boardState))
    }

    @Test
    fun checkWin_identifiesDiagonalWin() {
        // Arrange
        val boardState = listOf(
            listOf(SquareState.Cross, SquareState.None, SquareState.None),
            listOf(SquareState.None, SquareState.Cross, SquareState.None),
            listOf(SquareState.None, SquareState.None, SquareState.Cross)
        )
        // Act & Assert
        assertEquals(SquareState.Cross, checkWin(boardState))
    }

    @Test
    fun checkWin_identifiesNoWin() {
        // Arrange
        val boardState = listOf(
            listOf(SquareState.Cross, SquareState.Circle, SquareState.Cross),
            listOf(SquareState.Circle, SquareState.Cross, SquareState.Circle),
            listOf(SquareState.Circle, SquareState.Cross, SquareState.Circle)
        )
        // Act & Assert
        assertNull(checkWin(boardState))
    }
}
