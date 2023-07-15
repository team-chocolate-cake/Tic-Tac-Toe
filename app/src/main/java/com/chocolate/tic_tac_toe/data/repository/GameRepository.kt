package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.domain.model.Player
import kotlinx.coroutines.flow.Flow


interface GameRepository {
    // region Session
    // endregion

    // region Player

     fun getPlayers() : Flow<List<Player?>>
    // endregion
}