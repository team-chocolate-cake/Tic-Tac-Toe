package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    // region Session
    suspend fun createSession(session: Session)

    suspend fun getSession(id: String): Flow<Session>

    suspend fun joinSession(id: String, player: Player)

    suspend fun updateBoard(id: String, board: List<String>)

    suspend fun updateTurn(id: String,turn: String)

    suspend fun updateWinner(id: String, winnerId: String)

    suspend fun updateGameState(id: String, gameState: GameState)

    suspend fun updateWinPositions(id: String,positions: List<Int>)

    suspend fun deleteSession(id: String)
    // endregion

    // region Player
    suspend fun createPlayer(player: Player)

    suspend fun getPlayerPreviousNames(): List<String>?

    suspend fun getPlayerId(): String

    suspend fun getPlayers(): Flow<List<Player?>>

    suspend fun getPlayerData(): Flow<Player?>

    suspend fun updatePlayerName(name: String)

    suspend fun updatePlayerPreviousNames(name: String)

    suspend fun updateScore(playerId: String, score: Int)

    suspend fun updatePlayerState(playerId: String, playerState: Boolean)

    suspend fun getPlayerDataById(playerId: String): Player?

    fun getPlayerAvatars(): List<String>
    // region Player
}