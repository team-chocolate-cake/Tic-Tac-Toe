package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.GameState
import javax.inject.Inject

class UpdateWinnerUseCase @Inject constructor(
    private val gameRepository: GameRepository,
    private val updateSessionStateUseCase: UpdateSessionStateUseCase
) {
    suspend operator fun invoke(
        board: List<String>,
        playersId: List<String>,
        sessionId: String,
    ) {
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
                when (board[a]) {
                    "X" -> {
                        updateSessionStateUseCase(sessionId, GameState.PLAYER_X_WON)
                        gameRepository.updateWinner(sessionId, playersId[0])
                        gameRepository.updateScore(playersId.first(),10)
                    }

                    "O" -> {
                        updateSessionStateUseCase(sessionId, GameState.PLAYER_O_WON)
                        gameRepository.updateWinner(sessionId, playersId[1])
                        gameRepository.updateScore(playersId.last(),10)
                    }
                }
                gameRepository.updateWinPositions(positions, sessionId)
            }

        }

        if (!board.contains("")) {
            updateSessionStateUseCase(sessionId, GameState.DRAW)
        }
    }
}