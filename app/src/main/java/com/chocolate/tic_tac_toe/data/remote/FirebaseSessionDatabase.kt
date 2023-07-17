package com.chocolate.tic_tac_toe.data.remote

import com.chocolate.tic_tac_toe.domain.model.Session
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class FirebaseSessionDatabase @Inject constructor(
    @Named("sessions") private val sessionDatabaseReference: DatabaseReference,
) {
suspend fun createSession(session:Session): String{
   val sessionId= sessionDatabaseReference.push().key
    sessionDatabaseReference.child(sessionId!!).setValue(session).await()
    return sessionId
}
}