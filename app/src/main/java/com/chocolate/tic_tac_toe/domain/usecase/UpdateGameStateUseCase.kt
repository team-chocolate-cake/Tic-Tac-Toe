package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.GameState
import javax.inject.Inject

class UpdateGameStateUseCase @Inject constructor(
    private val gameRepository: GameRepository,
    private val checkGameState: CheckGameStateUseCase
) {
    suspend operator fun invoke(
        board: List<String>,
        index: Int,
        value: String,
        turn: String,
        xPlayerId: String,
        oPlayerId: String,
        sessionId: String,
    ) {
        val updatedBoard = board.toMutableList().also {
            it[index] = value
        }

        val updatedTurn = if (turn == xPlayerId) oPlayerId else xPlayerId

        when (checkGameState(updatedBoard)) {
            GameState.IN_PROGRESS -> {
                gameRepository.updateBoard(updatedBoard, sessionId)
                gameRepository.updateTurn(updatedTurn, sessionId)
            }

            GameState.PLAYER_X_WON -> {
                gameRepository.updateBoard(updatedBoard, sessionId)
                gameRepository.updateGameState(GameState.PLAYER_X_WON, sessionId)
            }

            GameState.PLAYER_O_WON -> {
                gameRepository.updateBoard(updatedBoard, sessionId)
                gameRepository.updateGameState(GameState.PLAYER_O_WON, sessionId)
            }

            GameState.DRAW -> {
                gameRepository.updateBoard(updatedBoard, sessionId)
                gameRepository.updateGameState(GameState.DRAW, sessionId)
            }
        }

    }
}