package com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel

data class EntryScreenUIState(
    val isLoading: Boolean = false,
    val isPlayerCreated: Boolean = false,
    val playerName: String = "",
    val playerPreviousNames: List<String> = emptyList(),
    val error: String? = null,
)
