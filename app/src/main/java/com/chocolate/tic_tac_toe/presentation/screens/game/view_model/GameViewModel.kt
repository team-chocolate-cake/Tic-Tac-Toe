package com.chocolate.tic_tac_toe.presentation.screens.game.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(GameUiState())
    val state = _state.asStateFlow()

    init {
        getSession()
    }

    private fun getSession() {
        viewModelScope.launch {
            gameRepository.getSession(SESSION_ID).collect { session ->
                _state.update {
                    it.copy(
                        xPlayer = session.xplayer!!.toPlayerUiState(),
                        oPlayer = session.oplayer!!.toPlayerUiState(),
                        turn = session.turn!!,
                        playerId = PLAYER_ID,
                        board = session.board ?: emptyList()
                    )
                }
            }
        }
    }

    fun updateBoard(index: Int) {
        _state.update {
            it.copy(
                board = it.board.toMutableList().apply {
                    this[index] = if (it.turn == it.oPlayer.id) "O" else "X"
                }
            )
        }
        viewModelScope.launch {
            gameRepository.updateBoard(_state.value.board, SESSION_ID)
        }
        updateTurn()
    }

    private fun updateTurn() {
        _state.update {
            it.copy(
                turn = if (it.turn == "X") "O" else "X"
            )
        }
        viewModelScope.launch {
            gameRepository.updateTurn(_state.value.turn, SESSION_ID)
        }
    }

    private fun Player.toPlayerUiState(): GameUiState.Player {
        return GameUiState.Player(
            id = this.id!!,
            name = this.name!!,
            score = this.score!!,
            type = this.type!!
        )
    }


    companion object {
        private const val SESSION_ID = "-N_PUYtRbaOphbvrjnin"
        private const val PLAYER_ID = "1689455042896"
    }
}
