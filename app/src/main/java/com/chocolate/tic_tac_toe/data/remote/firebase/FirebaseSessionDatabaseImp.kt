package com.chocolate.tic_tac_toe.data.remote.firebase

import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class FirebaseSessionDatabaseImp @Inject constructor(
    @Named("sessions") private val sessionDatabaseReference: DatabaseReference,
) : FirebaseSessionDatabase {
    override suspend fun createSession(session: Session) {
        sessionDatabaseReference.child(session.id).setValue(session).await()
    }

    override suspend fun getSession(id: String): Flow<Session> = callbackFlow {
        val boardRef = sessionDatabaseReference.child(id)

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val session = snapshot.getValue(Session::class.java)
                if (session == null) close()
                else trySend(session)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        boardRef.addValueEventListener(valueEventListener)
        awaitClose { boardRef.removeEventListener(valueEventListener) }
    }

    override suspend fun joinSession(id: String, player: Player) {
        sessionDatabaseReference.child(id).child(PLAYERS).child("1").setValue(player)
            .await()
    }

    override suspend fun updateBoard(id: String, board: List<String>) {
        sessionDatabaseReference.child(id).child(BOARD).setValue(board).await()
    }

    override suspend fun updateTurn(id: String, turn: String) {
        sessionDatabaseReference.child(id).child(TURN).setValue(turn).await()
    }

    override suspend fun updateWinner(id: String, winnerId: String) {
        sessionDatabaseReference.child(id).child(WINNER).setValue(winnerId).await()
    }

    override suspend fun updateGameState(id: String, gameState: GameState) {
        sessionDatabaseReference.child(id).child(STATE).setValue(gameState).await()
    }

    override suspend fun updateWinPositions(id: String, positions: List<Int>) {
        sessionDatabaseReference.child(id).child(WIN_POSITIONS).setValue(positions).await()
    }

    override suspend fun deleteSession(id: String) {
        sessionDatabaseReference.child(id).removeValue().await()
    }

    companion object {
        private const val BOARD = "board"
        private const val PLAYERS = "players"
        private const val TURN = "turn"
        private const val WINNER = "winner"
        private const val STATE = "state"
        private const val WIN_POSITIONS = "winPositions"
    }
}