package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.GameState
import javax.inject.Inject

class UpdateSessionState @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke(sessionId: String, playersId: Set<String>, gameState: GameState) {
        val playerId = gameRepository.getPlayerId()

        if (playerId == playersId.first() && gameState == GameState.WAITING_PLAYER_X ||
            playerId == playersId.last() && gameState == GameState.WAITING_PLAYER_O
        ) {
            gameRepository.updateBoard(List(9) { "" }, sessionId)
            gameRepository.updateGameState(sessionId,GameState.IN_PROGRESS)
            gameRepository.updateWinPositions(List(3) { -1 }, sessionId)
        } else if (playerId == playersId.first()) {
            gameRepository.updateGameState(sessionId,GameState.WAITING_PLAYER_O)
        } else {
            gameRepository.updateGameState(sessionId, GameState.WAITING_PLAYER_X)
        }
    }
}