package com.chocolate.tic_tac_toe.data.remote

import com.chocolate.tic_tac_toe.domain.model.Session
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class FirebaseSessionDatabase @Inject constructor(
    @Named("sessions") private val sessionDatabaseReference: DatabaseReference,
) {
    fun updateBoard(board: List<List<String>>, key: String) {
        sessionDatabaseReference.child(key).child("board").setValue(board)
    }

    fun getBoard(key: String): Flow<Session?> = callbackFlow {
        val boardRef = sessionDatabaseReference.child(key).child("board")
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val session = snapshot.getValue(Session::class.java)
                trySend(session)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        boardRef.addValueEventListener(valueEventListener)
        awaitClose { boardRef.removeEventListener(valueEventListener) }
    }
}