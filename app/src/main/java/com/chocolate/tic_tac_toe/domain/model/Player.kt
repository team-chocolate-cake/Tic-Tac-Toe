package com.chocolate.tic_tac_toe.domain.model

data class Player(
    val id: String? = null,
    val name: String? = null,
    val score: Int? = null,
    val symbol: String? = null,
    val createdSessionId: String? = null,
)