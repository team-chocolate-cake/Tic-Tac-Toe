package com.chocolate.tic_tac_toe.presentation.screens.game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val Route = "game"


fun NavController.navigateToGame() {
    navigate(Route)
}


fun NavGraphBuilder.gameRoute(
    navController: NavController
) {
    composable(Route) {
        GameScreen(navController = navController)
    }
}