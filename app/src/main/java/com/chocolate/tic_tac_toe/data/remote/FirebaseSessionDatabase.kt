package com.chocolate.tic_tac_toe.data.remote

import com.google.firebase.database.DatabaseReference
import javax.inject.Inject
import javax.inject.Named

class FirebaseSessionDatabase @Inject constructor(
    @Named("sessions") private val sessionDatabaseReference: DatabaseReference,
) {

}