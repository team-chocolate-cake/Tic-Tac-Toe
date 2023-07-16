package com.chocolate.tic_tac_toe.domain.model

data class Player(
    val name: String,
    val previewsNames: List<String>,
    val score: Int = 0,
    val createdSessionId: String = "",
)