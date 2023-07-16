package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.data.local.StorePlayerId
import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.data.remote.FirebaseSessionDatabase
import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class GameRepositoryImp @Inject constructor(
    private val storePlayerData: StorePlayerId,
    private val firebaseSessionDatabase: FirebaseSessionDatabase,
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) : GameRepository {
    // region Session

    // endregion

    // region Player
    override suspend fun createPlayer(player: Player) {
        val playerId = storePlayerData.getPlayerId()

        if (playerId == null) {
            firebasePlayerDatabase.createPlayer(player)
            storePlayerData.savePlayerId(player.id!!)
        } else {
            firebasePlayerDatabase.updatePlayerName(playerId, player.name!!)
        }
    }

    override suspend fun getPlayerData(): Player? {
        val playerId = storePlayerData.getPlayerId()
        return if (playerId == null) {
            null
        } else {
            firebasePlayerDatabase.getPlayerData(playerId)
        }
    }
    // endregion

}
