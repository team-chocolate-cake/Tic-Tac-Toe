package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {

    suspend operator fun invoke(
        id: String,
        name: String,
        imageUrl: String,
        score: Int,
    ) {
        gameRepository.createSession(
            Session(
                id = id,
                state = GameState.IN_PROGRESS,
                turn = id,
                players = listOf(
                    Player(
                        id = id,
                        name = name,
                        imageUrl = imageUrl,
                        score = score,
                        symbol = "X"
                    ), Player()
                )
            )
        )
    }

}