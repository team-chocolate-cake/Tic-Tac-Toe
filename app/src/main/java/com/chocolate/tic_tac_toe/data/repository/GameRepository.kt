package com.chocolate.tic_tac_toe.data.repository


interface GameRepository {
    // region Session

    fun updateBoard(board: List<List<String>>, key: String)
    // endregion

    // region Player
    // endregion
}