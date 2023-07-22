package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Session
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(playerId: String) {
        val player = gameRepository.getPlayerDataById(playerId)
        gameRepository.createSession(
            Session(
                id = playerId,
                turn = playerId,
                players = listOf(
                    player?.copy(symbol = "X") ?: throw Throwable("Player not found"),
                )
            )
        )
    }
}