package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.domain.model.Player


interface GameRepository {
    // region Session
    // endregion

    // region Player
    fun createPlayer(player: Player): String
    fun updatePlayerName(name: String)
    // endregion
}