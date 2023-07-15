package com.chocolate.tic_tac_toe.data.repository

import com.chocolate.tic_tac_toe.domain.model.Session
import kotlinx.coroutines.flow.Flow


interface GameRepository {
    // region Session

    fun updateBoard(board: List<List<String>>, key: String)

    // endregion
    fun getBoard(key: String): Flow<Session?>
    // region Player
    // endregion
}