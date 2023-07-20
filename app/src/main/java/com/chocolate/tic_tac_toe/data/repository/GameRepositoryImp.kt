package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.local.PlayerAvatars
import com.chocolate.tic_tac_toe.data.local.StorePlayerId
import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImp @Inject constructor(
    private val playerAvatars: PlayerAvatars,
    private val storePlayerData: StorePlayerId,
    private val firebaseSessionDatabase: FirebaseSessionDatabase,
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) : GameRepository {

    // region Session
    override suspend fun createSession(session: Session) {
        firebaseSessionDatabase.createSession(session)
    }
    // endregion

    // region Player
    override suspend fun createPlayer(player: Player) {
        val playerId = storePlayerData.getPlayerId()
        if (playerId == null) {
            firebasePlayerDatabase.createPlayer(player)
            storePlayerData.savePlayerId(player.id)
        }
    }


    override suspend fun updatePlayerName(name: String) {
        val playerId = storePlayerData.getPlayerId()
        if (playerId != null) {
            firebasePlayerDatabase.updatePlayerName(playerId, name)
        }
    }


    override suspend fun getPlayers(): Flow<List<Player?>> {
        return firebasePlayerDatabase.getPlayers()
    }

    override suspend fun getPlayerData(): Flow<Player> {
        val id = storePlayerData.getPlayerId()!!
        return firebasePlayerDatabase.getFLowPlayerDataById(id)
    }

    override suspend fun updatePlayerPreviousNames(name: String) {
        val id = storePlayerData.getPlayerId()!!
        firebasePlayerDatabase.updatePlayerPreviousNames(id, name)
    }

    override fun getPlayerAvatars(): List<String> {
        return playerAvatars.getPlayerAvatars()
    }

    override suspend fun updateWinner(sessionId: String, winnerId: String) {
        firebaseSessionDatabase.updateWinner(sessionId, winnerId)
    }
    //endregion

    override suspend fun getPlayerPreviousNames(): List<String>? {
        val playerId = storePlayerData.getPlayerId()
        return if (playerId == null) {
            null
        } else {
            firebasePlayerDatabase.getPlayerPreviousNames(playerId)
        }
    }

    // endregion
    override suspend fun updateBoard(board: List<String>, sessionId: String) {
        firebaseSessionDatabase.updateBoard(board, sessionId)
    }

    override suspend fun updateTurn(turn: String, sessionId: String) {
        firebaseSessionDatabase.updateTurn(turn, sessionId)
    }

    override suspend fun updateGameState(sessionId: String, gameState: GameState) {
        firebaseSessionDatabase.updateGameState(sessionId, gameState)
    }


    override suspend fun getSession(key: String): Flow<Session> {
        return firebaseSessionDatabase.getSession(key)
    }

    override suspend fun deleteSession(key: String) {
        return firebaseSessionDatabase.deleteSession(key)
    }

    override suspend fun updateWinPositions(positions: List<Int>, sessionId: String) {
        firebaseSessionDatabase.updateWinPositions(positions, sessionId)
    }

    override suspend fun joinSession(sessionId: String, player: Player) {
        firebaseSessionDatabase.joinSession(sessionId, player)
    }

    override suspend fun updatePlayerState(playerId: String, playerState: Boolean) {
        firebasePlayerDatabase.updatePlayerState(playerId, playerState)
    }

    override suspend fun getPlayerId(): String {
        return storePlayerData.getPlayerId()!!
    }

    override suspend fun getPlayerDataById(playerId: String): Player {
        return firebasePlayerDatabase.getPlayerDataById(playerId)
    }

}
