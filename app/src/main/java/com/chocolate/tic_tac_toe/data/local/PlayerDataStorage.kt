package com.chocolate.tic_tac_toe.data.local

interface PlayerDataStorage {
    suspend fun getPlayerId(): String?

    suspend fun savePlayerId(playerId: String)

    suspend fun clearPlayerId()
}