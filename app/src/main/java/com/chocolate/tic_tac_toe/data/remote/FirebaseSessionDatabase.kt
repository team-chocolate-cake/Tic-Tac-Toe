package com.chocolate.tic_tac_toe.data.remote

import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface FirebaseSessionDatabase {
    suspend fun createSession(session: Session)

    suspend fun getSession(id: String): Flow<Session>

    suspend fun joinSession(id: String, player: Player)

    suspend fun updateBoard(id: String, board: List<String>)

    suspend fun updateTurn(id: String, turn: String)

    suspend fun updateWinner(id: String, winnerId: String)

    suspend fun updateGameState(id: String, gameState: GameState)

    suspend fun updateWinPositions(id: String, positions: List<Int>)

    suspend fun deleteSession(id: String)
}