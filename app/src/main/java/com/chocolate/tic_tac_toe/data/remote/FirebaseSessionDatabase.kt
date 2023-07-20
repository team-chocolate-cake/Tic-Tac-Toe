package com.chocolate.tic_tac_toe.data.remote

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

class FirebaseSessionDatabase @Inject constructor(
    @Named("sessions") private val sessionDatabaseReference: DatabaseReference,
) {
    suspend fun updateBoard(board: List<String>, sessionId: String) {
        sessionDatabaseReference.child(sessionId).child("board").setValue(board).await()
    }

    suspend fun joinSession(sessionId: String, player: Player) {
        sessionDatabaseReference.child(sessionId).child("players").child("1").setValue(player)
            .await()
    }

    suspend fun updateTurn(turn: String, sessionId: String) {
        sessionDatabaseReference.child(sessionId).child("turn").setValue(turn).await()
    }

    suspend fun getSession(sessionId: String): Flow<Session> = callbackFlow {
        val boardRef = sessionDatabaseReference.child(sessionId)

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val session = snapshot.getValue(Session::class.java)
                if (session != null) {
                    trySend(session)
                } else {
                    close()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        boardRef.addValueEventListener(valueEventListener)
        awaitClose { boardRef.removeEventListener(valueEventListener) }
    }

    suspend fun createSession(session: Session) {
        sessionDatabaseReference.child(session.id).setValue(session).await()
    }

    suspend fun updateWinner(sessionId: String, winnerId: String) {
        sessionDatabaseReference.child(sessionId).child("winner").setValue(winnerId).await()
    }

    suspend fun updateGameState(sessionId: String, gameState: GameState) {
        sessionDatabaseReference.child(sessionId).child("state").setValue(gameState).await()
    }

    suspend fun updateWinPositions(positions: List<Int>, sessionId: String) {
        sessionDatabaseReference.child(sessionId).child("winPositions").setValue(positions).await()
    }

    suspend fun deleteSession(sessionId: String) {
        sessionDatabaseReference.child(sessionId).removeValue().await()
    }
}