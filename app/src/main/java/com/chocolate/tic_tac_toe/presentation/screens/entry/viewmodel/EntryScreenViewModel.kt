package com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.domain.usecase.CreatePlayerUseCase
import com.chocolate.tic_tac_toe.domain.usecase.GetPlayerPreviousNamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryScreenViewModel @Inject constructor(
    private val createPlayer: CreatePlayerUseCase,
    private val getPlayerPreviousNames: GetPlayerPreviousNamesUseCase
) :
    ViewModel() {
    private val _state = MutableStateFlow(EntryScreenUIState())
    val state = _state.asStateFlow()

    init {
        getPreviousNames()
    }

    fun createPlayer() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                createPlayer(
                    _state.value.playerName,
                    _state.value.playerPreviousNames
                )
            },
            onSuccess = ::onCreatePlayerSuccess,
            onError = ::onCreatePlayerError
        )
    }

    private fun onCreatePlayerSuccess(unit: Unit) {
        _state.update {
            it.copy(
                isLoading = false,
                isCreated = true
            )
        }
    }

    fun clearIsCreated() {
        _state.update { it.copy(isCreated = false) }
    }

    private fun onCreatePlayerError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

    private fun getPreviousNames() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getPlayerPreviousNames() },
            onSuccess = ::onGetPlayerPreviousNamesSuccess,
            onError = ::onGetPlayerPreviousNamesError
        )
    }

    private fun onGetPlayerPreviousNamesSuccess(previousNames: List<String>) {
        _state.update {
            it.copy(
                isLoading = false,
                playerName = previousNames.first(),
                playerPreviousNames = previousNames
            )
        }
    }

    private fun onGetPlayerPreviousNamesError(throwable: Throwable) {
        _state.update {
            it.copy(
                isLoading = false,
                error = throwable.message
            )
        }
        Log.i(
            "EntryScreenViewModel",
            "onGetPlayerPreviousNamesError: ${throwable.message}"

        )
    }

    fun onNameChange(newName: String) {
        _state.update { it.copy(playerName = newName) }
    }

    private fun <T> tryToExecute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                call().also(onSuccess)
            } catch (throwable: Throwable) {
                onError(throwable)
            }
        }
    }
}
