package com.chocolate.tic_tac_toe.domain.model

data class Session(
    val id: String,
    val players: Pair<Player, Player>,
    val board: List<List<String>>,
    val turn: Player,
    val winner: Player?,
    val state: Boolean,
    val playerRequest: Player?
)