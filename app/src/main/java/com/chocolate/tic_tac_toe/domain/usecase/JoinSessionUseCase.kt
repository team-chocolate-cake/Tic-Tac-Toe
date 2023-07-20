package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import javax.inject.Inject

class JoinSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(sessionId: String, playerId: String) {
        val player = gameRepository.getPlayerDataById(playerId)
        gameRepository.joinSession(sessionId, player.copy(symbol = "O"))
    }
}