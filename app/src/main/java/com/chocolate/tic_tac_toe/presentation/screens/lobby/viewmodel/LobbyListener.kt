package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel


interface LobbyListener  {
    fun onClickPlayer(sessionId: String)
    fun onClickCreateSession()
}