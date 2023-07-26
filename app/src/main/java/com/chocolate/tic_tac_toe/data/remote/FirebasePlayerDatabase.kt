package com.chocolate.tic_tac_toe.data.remote

import com.chocolate.tic_tac_toe.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface FirebasePlayerDatabase {
    suspend fun createPlayer(player: Player)

    suspend fun getPreviousPlayerNames(id: String): List<String>

    suspend fun getPlayerById(id: String): Player?

    suspend fun getPlayers(): Flow<List<Player?>>

    suspend fun getPlayerFLowById(id: String): Flow<Player?>

    suspend fun updatePlayerName(id: String, name: String)

    suspend fun updatePreviousPlayerNames(id: String, name: String)

    suspend fun updatePlayerScore(id: String, score: Int)

    suspend fun updatePlayerState(id: String, playerState: Boolean)
}