package com.chocolate.tic_tac_toe.presentation.screens.game.view_model

import com.chocolate.tic_tac_toe.domain.model.GameState

data class GameUiState(
    val players: List<Player> = emptyList(),
    val turn: String = "",
    val playerId: String = "",
    val gameState: GameState = GameState.IN_PROGRESS,
    val winPositions: List<Int> = emptyList(),
    val board: List<String> = emptyList()
) {
    data class Player(
        val id: String = "",
        val name: String = "",
        val symbol: String = "",
        val score: Int = 0,
    )
}

