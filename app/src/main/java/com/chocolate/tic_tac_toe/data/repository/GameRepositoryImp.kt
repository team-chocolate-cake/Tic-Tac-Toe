package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.local.PlayerAvatars
import com.chocolate.tic_tac_toe.data.local.PlayerDataStorage
import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImp @Inject constructor(
    private val playerAvatars: PlayerAvatars,
    private val storePlayerData: PlayerDataStorage,
    private val firebaseSessionDatabase: FirebaseSessionDatabase,
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) : GameRepository {

    // region Session
    override suspend fun createSession(session: Session) {
        firebaseSessionDatabase.createSession(session)
    }

    override suspend fun getSession(id: String): Flow<Session> {
        return firebaseSessionDatabase.getSession(id)
    }

    override suspend fun joinSession(id: String, player: Player) {
        firebaseSessionDatabase.joinSession(id, player)
    }

    override suspend fun updateBoard(id: String, board: List<String>) {
        firebaseSessionDatabase.updateBoard(id, board)
    }

    override suspend fun updateTurn(id: String, turn: String) {
        firebaseSessionDatabase.updateTurn(id, turn)
    }

    override suspend fun updateWinner(id: String, winnerId: String) {
        firebaseSessionDatabase.updateWinner(id, winnerId)
    }

    override suspend fun updateGameState(id: String, gameState: GameState) {
        firebaseSessionDatabase.updateGameState(id, gameState)
    }

    override suspend fun updateWinPositions(id: String, positions: List<Int>) {
        firebaseSessionDatabase.updateWinPositions(id, positions)
    }

    override suspend fun deleteSession(id: String) {
        return firebaseSessionDatabase.deleteSession(id)
    }
    // endregion

    // region Player
    override suspend fun createPlayer(player: Player) {
        val playerId = storePlayerData.getPlayerId()
        if (playerId == null) {
            storePlayerData.savePlayerId(player.id)
        }else{
            storePlayerData.clearPlayerId()
            storePlayerData.savePlayerId(player.id)
        }

        firebasePlayerDatabase.createPlayer(player)
    }

    override suspend fun updatePlayerPreviousNames(name: String) {
        val id = storePlayerData.getPlayerId()!!
        firebasePlayerDatabase.updatePlayerPreviousNames(id, name)
    }

    override suspend fun getPlayerId(): String {
        return storePlayerData.getPlayerId()!!
    }

    override suspend fun getPlayers(): Flow<List<Player?>> {
        return firebasePlayerDatabase.getPlayers()
    }

    override suspend fun getPlayerData(): Flow<Player?> {
        val id = storePlayerData.getPlayerId()!!
        return firebasePlayerDatabase.getPlayerFLowById(id)
    }

    override suspend fun updatePlayerName(name: String) {
        val playerId = storePlayerData.getPlayerId()!!
        firebasePlayerDatabase.updatePlayerName(playerId, name)
    }

    override suspend fun getPlayerPreviousNames(): List<String>? {
        val playerId = storePlayerData.getPlayerId()
        return if (playerId == null) {
            null
        } else {
            firebasePlayerDatabase.getPlayerPreviousNames(playerId)
        }
    }

    override suspend fun updateScore(playerId: String, score: Int) {
        firebasePlayerDatabase.updatePlayerScore(playerId, score)
    }

    override suspend fun updatePlayerState(playerId: String, playerState: Boolean) {
        firebasePlayerDatabase.updatePlayerState(playerId, playerState)
    }

    override suspend fun getPlayerDataById(playerId: String): Player? {
        return firebasePlayerDatabase.getPlayerById(playerId)
    }

    override fun getPlayerAvatars(): List<String> {
        return playerAvatars.getPlayerAvatars()
    }
    //endregion
}
