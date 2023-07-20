package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.domain.model.GameState
import javax.inject.Inject

class UpdateSessionPlayAgainUseCase @Inject constructor(
    private val updateSessionStateUseCase: UpdateSessionStateUseCase,
    private val restartSessionUseCase: RestartSessionUseCase
) {
    suspend operator fun invoke(
        playerId: String,
        playersId: List<String>,
        sessionId: String,
        gameState: GameState
    ) {
        if (playerId == playersId.first() && gameState == GameState.WAITING_PLAYER_X ||
            playerId == playersId.last() && gameState == GameState.WAITING_PLAYER_O
        ) {
            restartSessionUseCase(sessionId)
        } else if (playerId == playersId.first()) {
            updateSessionStateUseCase(sessionId, GameState.WAITING_PLAYER_O)
        } else {
            updateSessionStateUseCase(sessionId, GameState.WAITING_PLAYER_X)
        }
    }
}