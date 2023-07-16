package com.chocolate.tic_tac_toe.data.remote

import com.chocolate.tic_tac_toe.domain.model.Player
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.snapshots
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class FirebasePlayerDatabase @Inject constructor(
    @Named("players") private val firebaseDatabase: DatabaseReference
) {
    suspend fun createPlayer(player: Player) {
        firebaseDatabase.child(player.id!!).setValue(player).await()
    }

    suspend fun updatePlayerName(id: String, name: String) {
        firebaseDatabase.child(id).child("name").setValue(name).await()
        val previousNames = firebaseDatabase.child(id).child("previewsNames").get().await()

        previousNames.children.forEach {
            if (it.value == name) {
                return
            }
        }

        firebaseDatabase.child(id).child("previewsNames")
            .child(previousNames.childrenCount.toString()).setValue(name).await()
    }

    suspend fun getPlayerData(id: String): Player {
        return firebaseDatabase.child(id).get().await().getValue(Player::class.java)!!
    }
}