package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel

import com.chocolate.tic_tac_toe.domain.model.Session


interface LobbyListener  {
    fun onClickPlayer(sessionId: String)
    fun onClickCreateSession(session:Session)
}