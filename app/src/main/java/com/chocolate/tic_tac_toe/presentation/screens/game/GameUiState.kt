package com.chocolate.tic_tac_toe.presentation.screens.game

import com.chocolate.tic_tac_toe.domain.model.Player

data class GameUiState(
    val players: Pair<Player, Player> = Pair(
        Player("Player 1"),
        Player("Player 2")
    ),
    val board: List<List<String>> = emptyList()
)

