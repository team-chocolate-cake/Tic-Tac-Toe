package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel


data class LobbyUiState(
    val players: List<PlayerUiState> = emptyList(),
    val player: PlayerUiState = PlayerUiState(),
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