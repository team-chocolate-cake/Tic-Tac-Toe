package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow
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
