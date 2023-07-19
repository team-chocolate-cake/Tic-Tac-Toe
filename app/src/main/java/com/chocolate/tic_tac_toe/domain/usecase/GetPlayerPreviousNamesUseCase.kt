package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import javax.inject.Inject

class GetPlayerPreviousNamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(): List<String> {
        return gameRepository.getPlayerPreviousNames() ?: emptyList()
    }
}