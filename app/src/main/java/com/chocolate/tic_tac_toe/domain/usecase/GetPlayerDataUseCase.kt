package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Player
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayerDataUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(): Flow<Player?> {
        return gameRepository.getPlayerData()
    }
}