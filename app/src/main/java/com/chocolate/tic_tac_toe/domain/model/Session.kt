package com.chocolate.tic_tac_toe.domain.model

data class Session(
    val id: String? = null,
    val xplayer: Player? = null,
    val oplayer: Player? = null,
    val board: List<String>? = null,
    val turn: String? = null,
    val winner: Player? = null,
    val state: Boolean? = null,
    val playerRequest: Player? = null
)
