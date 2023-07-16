package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class GameRepositoryImp @Inject constructor(
    private val firebaseSessionDatabase: FirebaseSessionDatabase,
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) : GameRepository {
    // region Session

    // endregion

    // region Player
    override fun createPlayer(player: Player): String {
       return firebasePlayerDatabase.createPlayer(player)
    }

    override fun updatePlayerName(name: String) {
        return firebasePlayerDatabase.updatePlayerName("-N_P3iV4tgqBKBLoW_vU",name = name)
    }
    // endregion

}
