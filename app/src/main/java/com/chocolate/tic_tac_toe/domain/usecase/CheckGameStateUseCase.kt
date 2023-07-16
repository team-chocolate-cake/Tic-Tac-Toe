package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.domain.model.GameState
import javax.inject.Inject

class CheckGameStateUseCase @Inject constructor() {
    operator fun invoke(board: List<String>): GameState {
        val winningPositions = listOf(
            // Horizontal
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            // Vertical
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            // Diagonal
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        winningPositions.forEach { positions ->
            val (a, b, c) = positions
            if (board[a] != "" && board[a] == board[b] && board[a] == board[c]) {
                return when (board[a]) {
                    "X" -> GameState.PLAYER_X_WON
                    "O" -> GameState.PLAYER_O_WON
                    else -> throw IllegalStateException("Invalid player")
                }
            }
        }

        if (board.contains("")) {
            return GameState.IN_PROGRESS
        }

        return GameState.DRAW
    }
}