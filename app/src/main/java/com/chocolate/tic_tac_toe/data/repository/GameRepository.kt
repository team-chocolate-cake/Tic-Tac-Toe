package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow


interface GameRepository {
    // region Session
     suspend fun createSession(session: Session) : String
    // endregion

    // region Player
    suspend fun createPlayer(player: Player)
    suspend fun getPlayerData(): Player?
     fun getPlayers() : Flow<List<Player?>>
    suspend fun getPlayerById(id: String) : Flow<Player?>
    // endregion
}