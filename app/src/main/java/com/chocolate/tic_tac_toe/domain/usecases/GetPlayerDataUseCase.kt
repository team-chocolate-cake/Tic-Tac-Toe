package com.chocolate.tic_tac_toe.domain.usecases

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class GetPlayerDataUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(): List<String> {
        val player = gameRepository.getPlayerData()

        return if (player == null) {
            emptyList()
        } else {
            player.previewsNames!!
        }
    }
}