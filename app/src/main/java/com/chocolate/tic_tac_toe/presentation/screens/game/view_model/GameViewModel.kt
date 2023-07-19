package com.chocolate.tic_tac_toe.presentation.screens.game.view_model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.model.Session
import com.chocolate.tic_tac_toe.domain.usecase.DeleteSessionUseCase
import com.chocolate.tic_tac_toe.domain.usecase.EndGameStateUseCase
import com.chocolate.tic_tac_toe.domain.usecase.GetPlayerIdUseCase
import com.chocolate.tic_tac_toe.domain.usecase.GetSessionDataUseCase
import com.chocolate.tic_tac_toe.domain.usecase.UpdateGameStateUseCase
import com.chocolate.tic_tac_toe.domain.usecase.UpdateSessionState
import com.chocolate.tic_tac_toe.presentation.screens.game.GameArgs
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
class GameViewModel @Inject constructor(
    private val updateSessionState: UpdateSessionState,
    private val getPlayerIdUseCase: GetPlayerIdUseCase,
    private val getSessionDataUseCase: GetSessionDataUseCase,
    private val updateGameStateUseCase: UpdateGameStateUseCase,
    private val endGameStateUseCase: EndGameStateUseCase,
    private val deleteSessionUseCase: DeleteSessionUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(GameUiState())
    val state = _state.asStateFlow()

    private val args = GameArgs(savedStateHandle = savedStateHandle)

    init {
        getSessionData(args.id)
    }

    private fun getSessionData(id: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getSessionDataUseCase(id) },
            onSuccess = ::onGetSessionDataSuccess,
            onError = ::onGetSessionDataError
        )
    }

    private fun onGetSessionDataSuccess(sessionFlow: Flow<Session>) {
        _state.update { it.copy(isLoading = false, error = null) }
        viewModelScope.launch {
            val playerId = getPlayerIdUseCase()
            sessionFlow.collect { session ->
                _state.update {
                    it.copy(
                        players = session.players.map { player -> player.toPlayerUiState() },
                        turn = session.turn,
                        playerId = playerId,
                        gameState = session.state,
                        board = session.board,
                        winPositions = session.winPositions
                    )
                }
            }
        }
    }

    private fun onGetSessionDataError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

    fun updateGameState(index: Int, value: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                updateGameStateUseCase(
                    board = _state.value.board,
                    index = index,
                    value = value,
                    turn = _state.value.turn,
                    playersId = _state.value.players.map { it.id }.toSet(),
                    sessionId = args.id
                )
            },
            onSuccess = ::onUpdateGameStateSuccess,
            onError = ::onUpdateGameStateError
        )
    }

    private fun onUpdateGameStateSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, error = null) }
    }

    private fun onUpdateGameStateError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

    fun onPlayAgain() {
        tryToExecute(
            call = {
                updateSessionState(
                    args.id,
                    _state.value.players.map { it.id }.toSet(),
                    _state.value.gameState,
                )
            },
            onSuccess = ::onPlayAgainSuccess,
            onError = ::onPlayAgainError
        )
    }

    fun onClose() {
        tryToExecute(
            call = {
                endGameStateUseCase(args.id)
            },
            onSuccess = ::onDeleteSessionSuccess,
            onError = ::onDeleteSessionError
        )
    }
    fun onGameEnded() {
        tryToExecute(
            call = {
                deleteSessionUseCase(args.id)
            },
            onSuccess = ::onDeleteSessionSuccess,
            onError = ::onDeleteSessionError
        )
    }

    private fun onDeleteSessionError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

    private fun onDeleteSessionSuccess(unit: Unit) {
        _state.update { it.copy(gameState = GameState.IN_PROGRESS) }
    }

    private fun onPlayAgainSuccess(unit: Unit) {
    }

    private fun onPlayAgainError(throwable: Throwable) {

    }

    private fun Player.toPlayerUiState(): GameUiState.Player {
        return GameUiState.Player(
            id = this.id,
            name = this.name,
            score = this.score,
            symbol = this.symbol ?: "",
            imageUrl = this.imageUrl
        )
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
