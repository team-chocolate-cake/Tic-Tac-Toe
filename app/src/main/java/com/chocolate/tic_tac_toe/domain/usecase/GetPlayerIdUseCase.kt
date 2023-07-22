package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import javax.inject.Inject

class GetPlayerIdUseCase @Inject constructor(
    val gameRepository: GameRepository
) {
    suspend operator fun invoke(): String {
        return gameRepository.getPlayerId()
    }
}