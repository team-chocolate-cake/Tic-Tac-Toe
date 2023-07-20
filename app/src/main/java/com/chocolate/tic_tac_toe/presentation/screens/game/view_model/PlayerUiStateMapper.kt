package com.chocolate.tic_tac_toe.presentation.screens.game.view_model

import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class PlayerUiStateMapper @Inject constructor() {
    fun map(input: Player): GameUiState.Player {
        return GameUiState.Player(
            id = input.id,
            name = input.name,
            score = input.score,
            symbol = input.symbol ?: "",
            imageUrl = input.imageUrl,
        )
    }
}