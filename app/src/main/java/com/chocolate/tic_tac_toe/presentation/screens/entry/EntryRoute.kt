package com.chocolate.tic_tac_toe.presentation.screens.entry

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val Route = "entry"

fun NavGraphBuilder.entryRoute(
    navController: NavController
) {
    composable(Route) {
        EntryScreen(navController = navController) }
}
