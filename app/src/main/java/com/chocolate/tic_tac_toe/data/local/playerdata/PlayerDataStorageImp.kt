package com.chocolate.tic_tac_toe.data.local.playerdata

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.chocolate.tic_tac_toe.data.local.PlayerDataStorage
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PlayerDataStorageImp @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PlayerDataStorage {

    override suspend fun getPlayerId(): String? {
        val preferences = dataStore.data.first()
        return preferences[PLAYER_ID]
    }

    override suspend fun savePlayerId(playerId: String) {
        dataStore.edit { preferences ->
            preferences[PLAYER_ID] = playerId
        }
    }

    override suspend fun clearPlayerId() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val PLAYER_ID = stringPreferencesKey("Player_Id")
    }
}

