package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel

import com.chocolate.tic_tac_toe.domain.model.Player

data class LobbyUiState(
    val players: List<Player?> = emptyList(),
    val player: Player? = null,
    val error: String? = null,
    val isEmpty: Boolean = false,
    val isLoading: Boolean = false,
)

data class PlayerUiState(
    val id: String = "",
    val name: String = "",
    val score: Int = 0,
    val createdSessionId: String = "",
    val imageUrl: String = ""
)