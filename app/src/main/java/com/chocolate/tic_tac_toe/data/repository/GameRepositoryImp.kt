package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImp @Inject constructor(
    private val firebaseSessionDatabase: FirebaseSessionDatabase,
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) : GameRepository {
    // region Session

    // endregion

    // region Player

    // endregion
    override fun updateBoard(board: List<List<String>>, key: String) {
        firebaseSessionDatabase.updateBoard(board, key)
    }

    override fun getBoard(key: String): Flow<Session?> {
        return firebaseSessionDatabase.getBoard(key)
    }

}
