package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Session
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository
){

    suspend operator fun invoke() {
        val currentTimeMillis = System.currentTimeMillis()
        gameRepository.createSession(
            Session(
                id = currentTimeMillis.toString(),
                state = GameState.IN_PROGRESS
            )
        )
    }

}