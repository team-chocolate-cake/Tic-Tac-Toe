package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.GameState
import javax.inject.Inject

class EndGameStateUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(sessionId:String) {
        gameRepository.updateGameState(GameState.END, sessionId)
    }
}