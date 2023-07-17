package com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.domain.usecase.CreatePlayerUseCase
import com.chocolate.tic_tac_toe.domain.usecase.GetPlayerDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryScreenViewModel @Inject constructor(
    private val createPlayer: CreatePlayerUseCase,
    private val getPlayerData: GetPlayerDataUseCase
) :
    ViewModel() {
    private val _state = MutableStateFlow(EntryScreenUIState())
    val state = _state.asStateFlow()

    init {
        getPlayer()
    }

    fun createPlayer() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            createPlayer(_state.value.playerName)
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun getPlayer() {
        viewModelScope.launch {
            val names = getPlayerData()
            _state.update {
                it.copy(
                    previewsPlayerNames = names
                )
            }

            Log.i("player", "getPlayer: $names")
        }
    }

    fun onNameChange(newName: String) {
        _state.update { it.copy(playerName = newName) }
    }
}