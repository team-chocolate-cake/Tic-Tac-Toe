package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow


interface GameRepository {
    // region Session
    suspend fun createSession(session:Session)
    suspend fun updateBoard(board: List<String>, sessionId: String)

    suspend fun updateTurn(turn: String, sessionId: String)

    suspend fun getSession(key: String): Flow<Session>

    // endregion

    // region Player
    // endregion
}