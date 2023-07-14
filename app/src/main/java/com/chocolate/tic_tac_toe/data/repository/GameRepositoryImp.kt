package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import javax.inject.Inject

class GameRepositoryImp @Inject constructor(
    private val firebaseSessionDatabase: FirebaseSessionDatabase,
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) : GameRepository {
    // region Session

    // endregion

    // region Player

    // endregion
}
