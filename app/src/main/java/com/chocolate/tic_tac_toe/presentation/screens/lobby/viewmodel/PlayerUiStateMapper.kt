package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel

import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class PlayerUiStateMapper @Inject constructor() {
    fun map(input: Player): PlayerUiState {
        return PlayerUiState(
            id = input.id,
            name = input.name,
            score = input.score,
            imageUrl = input.imageUrl,
        )
    }
}