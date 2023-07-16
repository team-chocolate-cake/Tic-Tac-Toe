package com.chocolate.tic_tac_toe.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class StorePlayerId @Inject constructor(@ApplicationContext val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
    private val dataStore = context.dataStore


    suspend fun getPlayerId(): String? {
        val preferences = dataStore.data.first()
        return preferences[PLAYER_ID]
    }

    suspend fun savePlayerId(playerId: String) {
        dataStore.edit { preferences ->
            preferences[PLAYER_ID] = playerId
        }
    }

    suspend fun clearPlayerId() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val PLAYER_ID = stringPreferencesKey("Player_Id")
        private const val DATASTORE_NAME = "Player"
    }
}

