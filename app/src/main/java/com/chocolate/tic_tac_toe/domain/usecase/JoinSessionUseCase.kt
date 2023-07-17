package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class JoinSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(
        id: String,
        playerId: String,
        playerName: String,
        playerImageUrl: String,
        playerScore: Int,
    ) {
        gameRepository.joinSession(id,
            Player(
                id = playerId,
                name = playerName,
                imageUrl = playerImageUrl,
                score = playerScore,
                symbol = "O",
            )
            )
    }
}