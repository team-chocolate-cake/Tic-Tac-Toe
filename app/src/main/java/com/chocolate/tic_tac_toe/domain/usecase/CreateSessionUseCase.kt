package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke() {
        gameRepository.createSession()
        val playerId = gameRepository.getPlayerId()
        gameRepository.updatePlayerState(playerId, true)
    }
}