package com.chocolate.tic_tac_toe.domain.usecase

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class CreatePlayerUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(
        name: String,
        playerPreviousNames: List<String>
    ) {
    suspend operator fun invoke(name: String, imageUrl: String): Boolean {
        val playerId = System.currentTimeMillis().toString()

        if(playerPreviousNames.isNotEmpty()) {
            gameRepository.updatePlayerName(name)
        }else{
            val player = Player(
                id = playerId,
                name = name,
                previousNames = listOf(name),
                score = 0,
            )
            gameRepository.createPlayer(player)
        }



        val player = Player(
            id = playerId,
            name = name,
            previewsNames = listOf(name),
            score = 0,
            imageUrl = imageUrl,
        )
        return gameRepository.createPlayer(player)
    }
}