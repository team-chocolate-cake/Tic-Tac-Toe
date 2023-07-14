package com.chocolate.tic_tac_toe.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

//
val DarkPrimary = Color(0xFF9C3BE4)
val DarkOnPrimary = Color(0xFFFFFFFF)
val DarkBackground = Color(0xFF291973)
val DarkOnBackground87 = Color(0xDEF6F6F6)
val DarkOnBackground60 = Color(0x99F6F6F6)
val DarkOnBackground38 = Color(0x61F6F6F6)
val DarkSecondary = Color(0xFFE9C03C)
val DarkOnSecondary = Color(0xFFD83554)
val DarkOnCard = Color(0xFF302263)
val DarkCard = Color(0xFF624ABD)
val DarkCard87 = Color(0xDE624ABD)
val DarkOnBorder = Color(0xDECDC0E2)

@Immutable
data class CustomColorsPalette(
    val darkPrimary: Color = Color.Unspecified,
    val darkOnPrimary: Color = Color.Unspecified,
    val darkSecondary: Color = Color.Unspecified,
    val darkOnSecondary: Color = Color.Unspecified,
    val darkOnBackground87: Color = Color.Unspecified,
    val darkOnBackground60: Color = Color.Unspecified,
    val darkOnBackground38: Color = Color.Unspecified,
    val darkCard: Color = Color.Unspecified,
    val darkOnCard: Color = Color.Unspecified,
    val darkOnBorder: Color = Color.Unspecified,
    val darkBackground: Color = Color.Unspecified,
)
