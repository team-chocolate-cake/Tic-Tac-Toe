package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.GameState
import javax.inject.Inject

class RestartSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke(
        sessionId: String,
    ) {
        gameRepository.updateBoard(List(9) { "" }, sessionId)
        gameRepository.updateGameState(sessionId, GameState.IN_PROGRESS)
        gameRepository.updateWinPositions(List(3) { -1 }, sessionId)
        gameRepository.updateWinner(sessionId, "")
    }
}