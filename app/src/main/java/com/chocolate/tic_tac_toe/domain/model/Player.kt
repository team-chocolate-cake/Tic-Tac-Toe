package com.chocolate.tic_tac_toe.domain.model

data class Player(
    val id: String,
    val name: String,
    val imageUrl: String,
    val score: Int = 0,
    val createdSessionId: String = "",
)