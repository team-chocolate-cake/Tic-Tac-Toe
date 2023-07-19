package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import javax.inject.Inject

class GetPlayerAvatarUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(): String {
        return gameRepository.getPlayerAvatars().random()
    }
}