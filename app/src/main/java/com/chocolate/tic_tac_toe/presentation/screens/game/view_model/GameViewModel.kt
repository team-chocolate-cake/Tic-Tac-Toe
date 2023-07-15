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

    init {
        updateBoard()
    }

    private fun updateBoard() {
        gameRepository.updateBoard(
            listOf(listOf("X", "O", "X"), listOf("X", "O", "X"), listOf("X", "O", "X")),
            key
        )
    }

    companion object {
        private const val key = "-N_OIaJqsm7zT_Ik3TPp"
    }
}
