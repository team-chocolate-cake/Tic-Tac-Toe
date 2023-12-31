package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class CreatePlayerUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(
        name: String,
        playerPreviousNames: List<String>,
        imageUrl: String
    ) {
        if (playerPreviousNames.isNotEmpty() && playerPreviousNames.contains(name)) {
            gameRepository.updatePlayerName(name)
        } else if (playerPreviousNames.isNotEmpty() && !playerPreviousNames.contains(name)) {
            gameRepository.updatePlayerName(name)
            gameRepository.updatePlayerPreviousNames(name)
        } else {
            val playerId = System.currentTimeMillis().toString()

            val player = Player(
                id = playerId,
                name = name,
                previousNames = listOf(name),
                score = 0,
                imageUrl = imageUrl
            )
            gameRepository.createPlayer(player)
        }
    }
}