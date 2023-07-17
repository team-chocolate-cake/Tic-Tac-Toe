package com.chocolate.tic_tac_toe.domain.usecase.lobby

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Player
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayerByIdUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(): Player {
        return gameRepository.getPlayerById()
    }
}