package com.chocolate.tic_tac_toe.data.remote

import com.google.firebase.database.DatabaseReference
import javax.inject.Inject
import javax.inject.Named

class FirebasePlayerDatabase @Inject constructor(
   @Named("players") private val firebaseDatabase: DatabaseReference
) {

}