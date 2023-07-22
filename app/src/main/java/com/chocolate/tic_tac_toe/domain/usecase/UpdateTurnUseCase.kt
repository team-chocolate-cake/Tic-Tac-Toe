package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import javax.inject.Inject

class UpdateTurnUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(
        turn: String,
        playersId: List<String>,
        sessionId: String
    ) {
        val updatedTurn = if (turn == playersId.first()) playersId.last() else playersId.first()

        gameRepository.updateTurn(sessionId, updatedTurn)
    }
}