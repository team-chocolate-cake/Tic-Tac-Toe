package com.chocolate.tic_tac_toe.presentation.screens.game.view_model

data class GameUiState(
    val xPlayer: Player = Player(),
    val oPlayer: Player = Player(),
    val turn: String = "",
    val playerId: String = "",
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

