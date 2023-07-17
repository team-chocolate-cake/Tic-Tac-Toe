package com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel

import androidx.annotation.DrawableRes

data class EntryScreenUIState(
    val playerName: String = "",
    val previewsPlayerNames: List<String> = emptyList(),
   @DrawableRes val userImage: Int = 0,
)
