package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.local.StorePlayerId
import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow
import okhttp3.internal.wait
import javax.inject.Inject

class GameRepositoryImp @Inject constructor(
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

        } else {
            firebasePlayerDatabase.updatePlayerName(playerId, player.name)
        }
    }

    override fun getPlayers(): Flow<List<Player?>> {
        return firebasePlayerDatabase.getPlayers()
    }

    override suspend fun getPlayerById(): Player {
        val id = storePlayerData.getPlayerId()!!
        return firebasePlayerDatabase.getPlayerById(id)
    }
    //endregion

    override suspend fun getPlayerData(): Player? {
        val playerId = storePlayerData.getPlayerId()
        return if (playerId == null) {
            null
        } else {
            firebasePlayerDatabase.getPlayerData(playerId)
        }
    }

    // endregion
    override suspend fun updateBoard(board: List<String>, sessionId: String) {
        firebaseSessionDatabase.updateBoard(board, sessionId)
    }

    override suspend fun updateTurn(turn: String, sessionId: String) {
        firebaseSessionDatabase.updateTurn(turn, sessionId)
    }

    override suspend fun updateGameState(gameState: GameState, sessionId: String) {
        firebaseSessionDatabase.updateGameState(gameState, sessionId)
    }


    override suspend fun getSession(key: String): Flow<Session> {
        return firebaseSessionDatabase.getSession(key)
    }

    override suspend fun updateWinPositions(positions: List<Int>, sessionId: String) {
        firebaseSessionDatabase.updateWinPositions(positions, sessionId)
    }

    override suspend fun joinSession(sessionId: String, player: Player) {
        firebaseSessionDatabase.joinSession(sessionId, player)
    }

}
