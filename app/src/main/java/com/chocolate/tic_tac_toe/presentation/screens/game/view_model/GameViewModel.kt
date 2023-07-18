package com.chocolate.tic_tac_toe.presentation.screens.game.view_model


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.usecase.GetSessionDataUseCase
import com.chocolate.tic_tac_toe.domain.usecase.UpdateGameStateUseCase
import com.chocolate.tic_tac_toe.presentation.screens.game.GameArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val getSessionDataUseCase: GetSessionDataUseCase,
    private val updateGameStateUseCase: UpdateGameStateUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(GameUiState())
    val state = _state.asStateFlow()

    private val args = GameArgs(savedStateHandle = savedStateHandle)

    init {
        getSessionData(args.id)
        Log.i("GameViewModel", "init: ${args.id}")
    }

    private fun getSessionData(id: String) {
        viewModelScope.launch {
            getSessionDataUseCase(id).collect { session ->
                _state.update {
                    it.copy(
                        players = session.players.map { player -> player.toPlayerUiState() },
                        turn = session.turn,
                        playerId = "1689625101134",
                        gameState = session.state,
                        board = session.board,
                        winPositions = session.winPositions
                    )
                }
            }
        }
    }

    fun updateGameState(index: Int, value: String) {
        viewModelScope.launch {
            updateGameStateUseCase(
                board = _state.value.board,
                index = index,
                value = value,
                turn = _state.value.turn,
                playersId = _state.value.players.map { it.id }.toSet(),
                sessionId = args.id
            )
        }
    }

    fun onClose() {

    }

    fun onPlayAgain() {
        viewModelScope.launch {
        }
    }


    private fun Player.toPlayerUiState(): GameUiState.Player {
        return GameUiState.Player(
            id = this.id,
            name = this.name,
            score = this.score,
            symbol = this.symbol ?: ""
        )
    }
}
