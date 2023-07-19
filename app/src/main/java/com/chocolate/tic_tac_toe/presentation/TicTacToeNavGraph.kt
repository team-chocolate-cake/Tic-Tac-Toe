package com.chocolate.tic_tac_toe.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.chocolate.tic_tac_toe.presentation.screens.entry.entryRoute
import com.chocolate.tic_tac_toe.presentation.screens.game.gameRoute
import com.chocolate.tic_tac_toe.presentation.screens.lobby.lobbyRoute

@Composable
fun TicTacToeNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "entry") {
        entryRoute(navController = navController)
        lobbyRoute(navController = navController)
        gameRoute(navController = navController)
    }
}