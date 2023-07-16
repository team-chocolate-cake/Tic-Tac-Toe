package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImp @Inject constructor(
    private val firebaseSessionDatabase: FirebaseSessionDatabase,
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) : GameRepository {

    // region Session
    override suspend fun createSession(session: Session): String {
        return firebaseSessionDatabase.createSession(session)
    }
    // endregion

    // region Player
    override  fun getPlayers(): Flow<List<Player?>> {
        return firebasePlayerDatabase.getPlayers()
    }

    override suspend fun getPlayerById(id: String): Flow<Player?> {
        return firebasePlayerDatabase.getPlayerById(id = id)
    }
    //endregion

    // endregion
}
