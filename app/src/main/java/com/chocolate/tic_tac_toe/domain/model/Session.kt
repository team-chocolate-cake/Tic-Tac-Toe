package com.chocolate.tic_tac_toe.domain.model

data class Session(
    val id: String? = null,
    val xplayer: Player? = null,
    val oplayer: Player? = null,
    val board: List<String>? = null,
    val turn: String? = null,
    val winner: Player? = null,
    val winPositions: List<Int>? = null,
    val state: GameState? = GameState.IN_PROGRESS,
    val playerRequest: Player? = null
)
