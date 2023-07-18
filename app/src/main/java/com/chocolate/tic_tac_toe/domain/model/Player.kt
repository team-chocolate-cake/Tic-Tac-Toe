package com.chocolate.tic_tac_toe.domain.model

data class Player(
    val id: String = "",
    val name: String = "",
    val previousNames: List<String>? = null,
    val score: Int = 0,
    val symbol: String? = null,
    val imageUrl: String = "",
)