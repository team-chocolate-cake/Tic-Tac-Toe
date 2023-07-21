package com.chocolate.tic_tac_toe.data.remote.firebase

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.domain.model.Player
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class FirebasePlayerDatabaseImp @Inject constructor(
    @Named("players") private val firebaseDatabase: DatabaseReference
) : FirebasePlayerDatabase {
    override suspend fun createPlayer(player: Player) {
        firebaseDatabase.child(player.id).setValue(player).await()
    }

    override suspend fun getPlayerPreviousNames(id: String): List<String> {
        return firebaseDatabase.child(id).child(PREVIOUS_NAMES).get().await().children.map {
            it.value as String
        }
    }

    override suspend fun getPlayerById(id: String): Player? {
        return firebaseDatabase.child(id).get().await().getValue(Player::class.java)
    }

    override suspend fun getPlayers(): Flow<List<Player?>> = callbackFlow {

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

    override suspend fun getPlayerFLowById(id: String): Flow<Player?> = callbackFlow {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val player = snapshot.getValue(Player::class.java)
                trySend(player)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }

        }
        firebaseDatabase.child(id).addValueEventListener(valueEventListener)
        awaitClose { firebaseDatabase.removeEventListener(valueEventListener) }
    }

    override suspend fun updatePlayerName(id: String, name: String) {
        firebaseDatabase.child(id).child(NAME).setValue(name).await()
    }

    override suspend fun updatePlayerPreviousNames(id: String, name: String) {
        val oldPreviousNames = firebaseDatabase.child(id).child(PREVIOUS_NAMES).get().await()
        firebaseDatabase.child(id).child(PREVIOUS_NAMES)
            .child(oldPreviousNames.childrenCount.toString()).setValue(name).await()
    }

    override suspend fun updatePlayerScore(id: String, score: Int) {
        firebaseDatabase.child(id).child(SCORE).setValue(ServerValue.increment(score.toLong()))
            .await()
    }

    override suspend fun updatePlayerState(id: String, playerState: Boolean) {
        firebaseDatabase.child(id).child(WAITING).setValue(playerState).await()
    }

    companion object {
        private const val PREVIOUS_NAMES = "previousNames"
        private const val NAME = "name"
        private const val SCORE = "score"
        private const val WAITING = "waiting"
    }
}
