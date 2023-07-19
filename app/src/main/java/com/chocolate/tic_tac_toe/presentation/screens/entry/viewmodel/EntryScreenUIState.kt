package com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel

import androidx.annotation.DrawableRes

data class EntryScreenUIState(
    val isLoading: Boolean = false,
    val isCreated: Boolean = false,
    val playerName: String = "",
    val playerPreviousNames: List<String> = emptyList(),
    @DrawableRes val userImage: Int = 0,
    val error: String? = null,
    val playerImageUrl: String = "",
)
