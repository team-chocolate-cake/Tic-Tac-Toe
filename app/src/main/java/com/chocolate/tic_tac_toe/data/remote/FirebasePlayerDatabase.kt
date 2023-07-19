package com.chocolate.tic_tac_toe.data.remote

import com.chocolate.tic_tac_toe.domain.model.Player
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class FirebasePlayerDatabase @Inject constructor(
    @Named("players") private val firebaseDatabase: DatabaseReference
) {
    suspend fun createPlayer(player: Player) {
        firebaseDatabase.child(player.id).setValue(player).await()
    }

    suspend fun updatePlayerName(id: String, name: String) {
        firebaseDatabase.child(id).child("name").setValue(name).await()
        val previousNames = firebaseDatabase.child(id).child("previewsNames").get().await()
        firebaseDatabase.child(id).child("previewsNames")
            .child(previousNames.childrenCount.toString()).setValue(name).await()
    }

    suspend fun getPlayerPreviousNames(id: String): List<String> {
        return firebaseDatabase.child(id).child("previousNames").get().await().children.map {
            it.value as String
        }
    }

    suspend fun updatePlayerState(playerId: String, playerState: Boolean) {
        firebaseDatabase.child(playerId).child("waiting").setValue(playerState).await()
    }

    fun getPlayers(): Flow<List<Player?>> = callbackFlow {

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val players = snapshot.children.map {
                    it.getValue(Player::class.java)
                }.sortedByDescending { it?.score }
                trySend(players)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        firebaseDatabase.addValueEventListener(valueEventListener)
        awaitClose { firebaseDatabase.removeEventListener(valueEventListener) }
    }

    suspend fun getPlayerDataById(id: String): Player {
        return firebaseDatabase.child(id).get().await().getValue(Player::class.java)!!
    }
}
