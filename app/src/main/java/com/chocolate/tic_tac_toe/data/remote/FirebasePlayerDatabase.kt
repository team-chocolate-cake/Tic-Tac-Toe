package com.chocolate.tic_tac_toe.data.remote

import com.chocolate.tic_tac_toe.domain.model.Player
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject
import javax.inject.Named

class FirebasePlayerDatabase @Inject constructor(
    @Named("players") private val firebaseDatabase: DatabaseReference
) {
    fun createPlayer(player: Player): String {
        val id = firebaseDatabase.push().key
        firebaseDatabase.child(id!!).setValue(player)
        return id
    }

    fun updatePlayerName(id: String, name: String) {
        firebaseDatabase.child(id).child("name").setValue(name)
        firebaseDatabase.child(id).child("previewsNames").push().setValue(name)
    }
}