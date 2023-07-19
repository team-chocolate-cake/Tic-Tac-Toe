package com.chocolate.tic_tac_toe.presentation.screens.game

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val Route = "game"


fun NavController.navigateToGame(id:String) {
    navigate("$Route/$id")
}

fun NavGraphBuilder.gameRoute(
    navController: NavController
) {
    composable("$Route/{${GameArgs.ID}}", arguments = listOf(
        navArgument(GameArgs.ID) {
            type = NavType.StringType
        }
    )
    ) {
        GameScreen(navController = navController)
    }
}

class GameArgs(savedStateHandle: SavedStateHandle) {
    val id: String = checkNotNull(savedStateHandle[ID])

    companion object {
        const val ID = "id"
    }
}