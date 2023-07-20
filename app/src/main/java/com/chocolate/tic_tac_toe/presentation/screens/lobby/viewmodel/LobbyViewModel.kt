package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.usecase.CreateSessionUseCase
import com.chocolate.tic_tac_toe.domain.usecase.JoinSessionUseCase
import com.chocolate.tic_tac_toe.domain.usecase.UpdatePlayerStateUseCase
import com.chocolate.tic_tac_toe.domain.usecase.UpdateSessionStateUseCase
import com.chocolate.tic_tac_toe.domain.usecase.lobby.GetPlayerDataUseCase
import com.chocolate.tic_tac_toe.domain.usecase.lobby.GetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val getPlayerDataUseCase: GetPlayerDataUseCase,
    private val getPlayersUseCase: GetPlayersUseCase,
    private val joinSessionUseCase: JoinSessionUseCase,
    private val updatePlayerStateUseCase: UpdatePlayerStateUseCase,
    private val updateSessionStateUseCase: UpdateSessionStateUseCase,
    private val createSessionUseCase: CreateSessionUseCase,
    private val playerUiStateMapper: PlayerUiStateMapper,
) : ViewModel(), LobbyListener {

    private val _state = MutableStateFlow(LobbyUiState())
    val state = _state.asStateFlow()

    init {
        getPlayerData()
        getPlayers()
    }

    private fun getPlayerData() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getPlayerDataUseCase() },
            onSuccess = ::onGetPlayerDataSuccess,
            onError = ::onGetPlayerDataError
        )
    }

    private fun onGetPlayerDataSuccess(player: Flow<Player>) {
        viewModelScope.launch {
            player.collect { player ->
                _state.update {
                    it.copy(
                        player = playerUiStateMapper.map(player),
                        isLoading = false,
                        error = null,
                    )
                }
            }
        }
    }

    private fun onGetPlayerDataError(throwable: Throwable) {
        _state.update {
            it.copy(
                isLoading = false,
                error = throwable.message
            )
        }
    }

    private fun getPlayers() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getPlayersUseCase() },
            onSuccess = ::onGetPlayersSuccess,
            onError = ::onGetPlayersError
        )
    }

    private fun onGetPlayersError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }

    }

    private fun onGetPlayersSuccess(playersFlow: Flow<List<Player?>>) {
        viewModelScope.launch {
            playersFlow.collect { players ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        players = players.map { player -> playerUiStateMapper.map(player!!) }
                    )
                }
            }
        }
    }

    override fun onClickPlayer(sessionId: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                joinSessionUseCase(sessionId, _state.value.player.id)
                updatePlayerStateUseCase(sessionId, false)
                updateSessionStateUseCase(sessionId, GameState.IN_PROGRESS)
            },
            onSuccess = ::onJoinSessionSuccess,
            onError = ::onJoinSessionError
        )
    }

    override fun onClickCreateSession() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                createSessionUseCase(_state.value.player.id)
                updatePlayerStateUseCase(_state.value.player.id, true)
            },
            onSuccess = ::onCreateSessionSuccess,
            onError = ::onCreateSessionError
        )
    }

    private fun onCreateSessionSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, isSessionCreated = true) }
    }

    fun clearIsSessionCreated() {
        _state.update { it.copy(isSessionCreated = false) }
    }

    private fun onCreateSessionError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }


    private fun onJoinSessionSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, isSessionJoined = true) }
    }

    private fun onJoinSessionError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
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