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
            if (it[index] == "") {
                it[index] = value
            } else {
                return
            }
        }

        val updatedTurn = if (turn == xPlayerId) oPlayerId else xPlayerId

        checkGameState(updatedBoard, sessionId)
        gameRepository.updateBoard(updatedBoard, sessionId)
        gameRepository.updateTurn(updatedTurn, sessionId)
    }
}