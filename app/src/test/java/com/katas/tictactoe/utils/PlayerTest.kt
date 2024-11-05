package com.katas.tictactoe.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

class PlayerTest {

    @Test
    fun playerEnum_hasCorrectValues() {
        assertEquals(Player.Player1, Player.valueOf("Player1"))
        assertEquals(Player.Player2, Player.valueOf("Player2"))
    }
}
