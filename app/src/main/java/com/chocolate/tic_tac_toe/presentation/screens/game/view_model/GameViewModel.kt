package com.chocolate.tic_tac_toe.presentation.screens.game.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.domain.usecase.UpdateTurnUseCase
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.domain.model.Session
import com.chocolate.tic_tac_toe.domain.usecase.DeleteSessionUseCase
import com.chocolate.tic_tac_toe.domain.usecase.GetPlayerIdUseCase
import com.chocolate.tic_tac_toe.domain.usecase.GetSessionDataUseCase
import com.chocolate.tic_tac_toe.domain.usecase.UpdateBoardUseCase
import com.chocolate.tic_tac_toe.domain.usecase.UpdateSessionPlayAgainUseCase
import com.chocolate.tic_tac_toe.domain.usecase.UpdateSessionStateUseCase
import com.chocolate.tic_tac_toe.domain.usecase.UpdateWinnerUseCase
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
    private val getSessionDataUseCase: GetSessionDataUseCase,
    private val updateSessionPlayAgainUseCase: UpdateSessionPlayAgainUseCase,
    private val updateTurnUseCase: UpdateTurnUseCase,
    private val updateWinnerUseCase: UpdateWinnerUseCase,
    private val getPlayerIdUseCase: GetPlayerIdUseCase,
    private val updateBoardUseCase: UpdateBoardUseCase,
    private val updateSessionStateUseCase: UpdateSessionStateUseCase,
    private val deleteSessionUseCase: DeleteSessionUseCase,
    private val playerUiStateMapper: PlayerUiStateMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(GameUiState())
    val state = _state.asStateFlow()

    private val sessionId = GameArgs(savedStateHandle = savedStateHandle).id

    init {
        getSessionData(sessionId)
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
                        players = session.players.map { player ->
                            playerUiStateMapper.map(player)
                        },
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
        _state.update {
            it.copy(
                isLoading = false,
                isSessionClosed = true,
                error = throwable.message
            )
        }
    }

    fun updateGameState(index: Int, value: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                updateBoardUseCase(
                    id = sessionId,
                    board = _state.value.board,
                    index = index,
                    value = value,
                )
                updateTurnUseCase(
                    turn = _state.value.turn,
                    playersId = _state.value.players.map { it.id },
                    sessionId = sessionId
                )
                updateWinnerUseCase(
                    board = _state.value.board,
                    playersId = _state.value.players.map { it.id },
                    sessionId = sessionId
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
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                updateSessionPlayAgainUseCase(
                    playerId = _state.value.playerId,
                    sessionId = sessionId,
                    playersId = _state.value.players.map { it.id },
                    gameState = _state.value.gameState
                )
            },
            onSuccess = ::onPlayAgainSuccess,
            onError = ::onPlayAgainError
        )
    }

    fun onClose() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { updateSessionStateUseCase(sessionId, GameState.END) },
            onSuccess = ::onDeleteSessionSuccess,
            onError = ::onDeleteSessionError
        )
    }

    fun onGameEnded() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { deleteSessionUseCase(sessionId) },
            onSuccess = ::onDeleteSessionSuccess,
            onError = ::onDeleteSessionError
        )
    }

    private fun onDeleteSessionError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

    private fun onDeleteSessionSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false) }
    }

    private fun onPlayAgainSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, error = null) }
    }

    private fun onPlayAgainError(throwable: Throwable) {
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
