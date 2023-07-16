package com.chocolate.tic_tac_toe.data.remote

import com.chocolate.tic_tac_toe.domain.model.Player
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class FirebasePlayerDatabase @Inject constructor(
    @Named("players") private val firebaseDatabase: DatabaseReference
) {

    fun getPlayers(): Flow<List<Player?>> = callbackFlow {

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val players = snapshot.children.map {
                    it.getValue(Player::class.java)
                }
                trySend(players)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        firebaseDatabase.addValueEventListener(valueEventListener)
        awaitClose { firebaseDatabase.removeEventListener(valueEventListener) }
    }


    suspend fun getPlayerById(id: String): Flow<Player?> = callbackFlow {

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val player = snapshot.getValue(Player::class.java)
                trySend(player)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        firebaseDatabase.addValueEventListener(valueEventListener)
        awaitClose { firebaseDatabase.removeEventListener(valueEventListener) }
    }
}
