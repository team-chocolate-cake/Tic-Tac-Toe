package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow


interface GameRepository {
    // region Session
    suspend fun createSession()

    suspend fun updateBoard(board: List<String>, sessionId: String)

    suspend fun updateTurn(turn: String, sessionId: String)

    suspend fun updateGameState(gameState: GameState, sessionId: String)

    suspend fun getSession(key: String): Flow<Session>
    suspend fun deleteSession(key: String)

    suspend fun updateWinPositions(positions: List<Int>, sessionId: String)

    suspend fun joinSession(sessionId: String)

    suspend fun updatePlayerState(playerId: String, playerState: Boolean)

    suspend fun getPlayerId(): String

    // endregion

    // region Player
    suspend fun createPlayer(player: Player)
    suspend fun getPlayerPreviousNames(): List<String>?
    suspend fun updatePlayerName(name: String)
    fun getPlayers(): Flow<List<Player?>>
    suspend fun getPlayerData(): Flow<Player>
    // endregion

    fun getPlayerAvatars(): List<String>
}