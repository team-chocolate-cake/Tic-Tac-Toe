package com.chocolate.tic_tac_toe.data.repository


interface GameRepository {
    // region Session
    suspend fun createSession(session: Session)

    suspend fun updateBoard(board: List<String>, sessionId: String)

    suspend fun updateTurn(turn: String, sessionId: String)

    suspend fun updateGameState(gameState: GameState, sessionId: String)

    suspend fun getSession(key: String): Flow<Session>

    suspend fun updateWinPositions(positions: List<Int>, sessionId: String)

    // endregion

    // region Player
    suspend fun createPlayer(player: Player)
    suspend fun getPlayerData(): Player?
     fun getPlayers() : Flow<List<Player?>>
    suspend fun getPlayerById(id: String) : Flow<Player?>
    // endregion
}