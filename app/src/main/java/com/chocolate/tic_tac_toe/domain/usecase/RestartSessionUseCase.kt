package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.GameState
import javax.inject.Inject

class RestartSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke(
        id: String,
    ) {
        gameRepository.updateBoard(id, List(9) { "" })
        gameRepository.updateGameState(id, GameState.IN_PROGRESS)
        gameRepository.updateWinPositions(id, List(3) { -1 })
        gameRepository.updateWinner(id, "")
    }
}