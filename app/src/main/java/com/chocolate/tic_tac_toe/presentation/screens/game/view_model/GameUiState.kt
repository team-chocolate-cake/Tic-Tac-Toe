package com.chocolate.tic_tac_toe.presentation.screens.game.view_model

import com.chocolate.tic_tac_toe.domain.model.GameState

data class GameUiState(
    val isLoading: Boolean = false,
    val players: List<Player> = emptyList(),
    val turn: String = "",
    val playerId: String = "",
    val gameState: GameState = GameState.WAITING_FOR_PLAYERS,
    val winPositions: List<Int> = emptyList(),
    val board: List<String> = emptyList(),
    val error: String? = null,
) {
    data class Player(
        val id: String = "",
        val name: String = "",
        val symbol: String = "",
        val score: Int = 0,
        val imageUrl: String = "",
    )
}

