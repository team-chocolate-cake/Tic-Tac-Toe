package com.chocolate.tic_tac_toe.presentation.screens.game.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.presentation.screens.game.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
) : ViewModel() {
    private val _gameUiState = MutableStateFlow<GameUiState?>(null)
    val gameUiState = _gameUiState.asStateFlow()

    init {
        updateBoard()
        getBoard()
    }

    private fun updateBoard() {
        gameRepository.updateBoard(
            listOf(listOf("X", "O", "X"), listOf("X", "O", "X"), listOf("X", "O", "X")),
            key
        )
    }


    private fun getBoard() {
        viewModelScope.launch {
            gameRepository.getBoard(key).collect { session ->
                val board = session?.board
                val updatedGameUiState = GameUiState(board = board ?: emptyList())
                _gameUiState.value = updatedGameUiState
            }
        }
    }

    companion object {
        private const val key = "-N_OIaJqsm7zT_Ik3TPp"
    }
}
