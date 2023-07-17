package com.chocolate.tic_tac_toe.presentation.screens.lobby

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val Route = "lobby"


fun NavController.navigateToLobby() {
    navigate(Route)
}


fun NavGraphBuilder.lobbyRoute(
    navController: NavController
) {
    composable(Route) {
        LobbyScreen(navController = navController)
    }
}