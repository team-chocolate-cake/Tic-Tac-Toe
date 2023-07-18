package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class JoinSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(sessionId: String) {
        gameRepository.joinSession(sessionId)
        gameRepository.updatePlayerState(sessionId, false)
    }
}