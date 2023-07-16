package com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel

import androidx.lifecycle.ViewModel
import com.chocolate.tic_tac_toe.domain.usecases.CreatePlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EntryScreenViewModel @Inject constructor(private val createPlayer: CreatePlayerUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(EntryScreenUIState())
    val state = _state.asStateFlow()



    fun cratePlayer(){
        createPlayer(_state.value.playerName)
    }

    fun onNameChange(newName: String) {
        _state.update { it.copy(playerName = newName) }
    }
}