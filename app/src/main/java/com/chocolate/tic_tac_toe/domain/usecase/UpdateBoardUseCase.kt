package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import javax.inject.Inject

class UpdateBoardUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke(
        id: String,
        board: List<String>,
        index: Int,
        value: String
    ) {
        val updatedBoard = board.toMutableList().also {
            if (it[index].isBlank()) {
                it[index] = value
            } else {
                return
            }
        }
        gameRepository.updateBoard(id, updatedBoard)
    }
}