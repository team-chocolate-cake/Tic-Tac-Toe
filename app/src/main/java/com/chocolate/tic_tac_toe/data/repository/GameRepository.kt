package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow


interface GameRepository {
    // region Session
    suspend fun createSession(session:Session)

    suspend fun updateBoard(board: List<String>, sessionId: String)

    suspend fun updateTurn(turn: String, sessionId: String)

    suspend fun updateGameState(sessionId: String, gameState: GameState)

    suspend fun getSession(key: String): Flow<Session>
    suspend fun deleteSession(key: String)

    suspend fun updateWinPositions(positions: List<Int>, sessionId: String)

    suspend fun joinSession(sessionId: String, player: Player)

    suspend fun updatePlayerState(playerId: String, playerState: Boolean)

    suspend fun getPlayerId(): String

    // endregion

    // region Player
    suspend fun createPlayer(player: Player)
    suspend fun getPlayerPreviousNames(): List<String>?
    suspend fun updatePlayerName(name: String)
    suspend fun getPlayers(): Flow<List<Player?>>
    suspend fun getPlayerData(): Flow<Player>

    suspend fun getPlayerDataById(playerId: String): Player
    // endregion

    fun getPlayerAvatars(): List<String>

    suspend fun updateWinner(sessionId: String, winnerId: String)
}