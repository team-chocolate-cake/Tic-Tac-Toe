package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.domain.model.Player


interface GameRepository {
    // region Session
    // endregion

    // region Player
    suspend fun createPlayer(player: Player)
    suspend fun getPlayerData(): Player?
    // endregion
}