package com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel

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
    private val createPlayerUseCase: CreatePlayerUseCase,
    private val getPlayerPreviousNamesUseCase: GetPlayerPreviousNamesUseCase
) :
    ViewModel() {
    private val _state = MutableStateFlow(EntryScreenUIState())
    val state = _state.asStateFlow()

    init {
        getPreviousNames()
        getImage()
    }

    fun createPlayer() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                createPlayerUseCase(
                    _state.value.playerName,
                    _state.value.playerPreviousNames,
                    _state.value.playerImageUrl
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
            call = { getPlayerPreviousNamesUseCase() },
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
    }

    private fun getImage() {
        val imageUrls = listOf(
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838240304312382/avatar_1.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838240526602261/avatar_2.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838240757305386/avatar_3.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838241034121216/avatar_4.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838241239633982/avatar_5.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838241457754152/avatar_6.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838241692626944/avatar_7.png",
        )
        _state.update { it.copy(playerImageUrl = imageUrls.random()) }
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