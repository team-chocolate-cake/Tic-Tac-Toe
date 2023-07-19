package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel


data class LobbyUiState(
    val players: List<PlayerUiState> = emptyList(),
    val player: PlayerUiState = PlayerUiState(),
    val error: String? = null,
    val isSessionCreated : Boolean = false,
    val isSessionJoined : Boolean = false,
    val isEmpty: Boolean = false,
    val isLoading: Boolean = false,
)

data class PlayerUiState(
    val id: String = "",
    val name: String = "",
    val score: Int = 0,
    val imageUrl: String = "",
    val isWaiting: Boolean = false,
)