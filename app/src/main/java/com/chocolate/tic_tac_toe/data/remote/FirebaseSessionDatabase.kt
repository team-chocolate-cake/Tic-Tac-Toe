package com.chocolate.tic_tac_toe.data.remote

import com.google.firebase.database.DatabaseReference
import javax.inject.Inject
import javax.inject.Named

class FirebaseSessionDatabase @Inject constructor(
    @Named("sessions") private val sessionDatabaseReference: DatabaseReference,
) {
    suspend fun updateBoard(board: List<String>, sessionId: String) {
        sessionDatabaseReference.child(sessionId).child("board").setValue(board).await()
    }

    suspend fun updateTurn(turn: String, sessionId: String) {
        sessionDatabaseReference.child(sessionId).child("turn").setValue(turn).await()
    }

    suspend fun getSession(sessionId: String): Flow<Session> = callbackFlow {
        val boardRef = sessionDatabaseReference.child(sessionId)

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val session = snapshot.getValue(Session::class.java)
                trySend(session!!)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        boardRef.addValueEventListener(valueEventListener)
        awaitClose { boardRef.removeEventListener(valueEventListener) }
    }

    suspend fun createSession(session: Session) {
        sessionDatabaseReference.child(session.id.toString()).setValue(session).await()
    }

    suspend fun updateGameState(gameState: GameState, sessionId: String) {
        sessionDatabaseReference.child(sessionId).child("state").setValue(gameState).await()
    }

    suspend fun updateWinPositions(positions: List<Int>, sessionId: String) {
        sessionDatabaseReference.child(sessionId).child("winPositions").setValue(positions).await()
    }
}