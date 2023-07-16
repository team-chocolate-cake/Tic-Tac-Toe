package com.chocolate.tic_tac_toe.domain.model

data class Session(
    val id: String,
    val players: Pair<Player, Player>?=null,
    val board: List<List<String>>?=null,
    val turn: Player?=null,
    val winner: Player?=null,
    val state: Boolean?=null,
    val playerRequest: Player?=null
)