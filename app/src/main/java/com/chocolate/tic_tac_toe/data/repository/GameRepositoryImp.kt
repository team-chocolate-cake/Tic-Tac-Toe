package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import javax.inject.Inject

class GameRepositoryImp @Inject constructor(
    private val firebaseSessionDatabase: FirebaseSessionDatabase,
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) : GameRepository {
    // region Session
    override suspend fun createSession(session:Session) {
        firebaseSessionDatabase.createSession(session)
    }
    // endregion

    // region Player
    override  fun getPlayers(): Flow<List<Player?>> {
        return firebasePlayerDatabase.getPlayers()
    }

    override suspend fun getPlayerById(id: String): Flow<Player?> {
        return firebasePlayerDatabase.getPlayerById(id = id)
    }
    //endregion

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

}
