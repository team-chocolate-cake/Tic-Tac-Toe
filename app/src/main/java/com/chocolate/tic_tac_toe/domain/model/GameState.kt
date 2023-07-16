package com.chocolate.tic_tac_toe.domain.model

enum class GameState {
    WAITING_FOR_PLAYERS,
    IN_PROGRESS,
    PLAYER_X_WON,
    PLAYER_O_WON,
    DRAW
}