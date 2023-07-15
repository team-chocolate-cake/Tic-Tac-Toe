package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel

import com.chocolate.tic_tac_toe.domain.model.Player

data class LobbyUiState(
    val players: List<Player> = emptyList(),
    val error : String = "",
    val isEmpty: Boolean = false,
){
    data class PlayerUiState(
        val name: String ="",
        val score: Int = 0,
        val createdSessionId: String = "",
        val imageUrl: String = ""
    )
}