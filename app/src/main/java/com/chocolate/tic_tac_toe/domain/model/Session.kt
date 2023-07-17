package com.chocolate.tic_tac_toe.domain.model

data class Session(
    val id: String = "",
    val players: List<Player> = List(2) { Player() },
    val board: List<String> = List(9) { "" },
    val turn: String = "",
    val winner: Player? = null,
    val winPositions: List<Int> = List(3) { -1 },
    val state: GameState = GameState.IN_PROGRESS,
    val playerRequest: Player? = null
)
