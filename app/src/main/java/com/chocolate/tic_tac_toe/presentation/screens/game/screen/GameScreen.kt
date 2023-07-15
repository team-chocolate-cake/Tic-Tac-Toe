package com.chocolate.tic_tac_toe.presentation.screens.game.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.tic_tac_toe.presentation.screens.game.components.ImageForBackground
import com.chocolate.tic_tac_toe.presentation.screens.game.components.LazyVerticalGridDemoContent
import com.chocolate.tic_tac_toe.presentation.screens.game.components.PlayersContent
import com.chocolate.tic_tac_toe.presentation.screens.game.view_model.GameViewModel
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkBackground

@SuppressLint("SuspiciousIndentation")
@Composable
fun GameScreen() {
    val viewModel: GameViewModel = hiltViewModel()
    val gameUiState by viewModel.gameUiState.collectAsState()
    val board = gameUiState?.board
    GameScreenContent(board)
}

@Composable
fun GameScreenContent(board: List<List<String>>?) {
    Box {
        ImageForBackground()
        Column(modifier = Modifier.background(color = DarkBackground)) {
            PlayersContent()
            if (board != null) {
                LazyVerticalGridDemoContent(board)
            } else {
                // Handle the case when board is null or not available
            }
        }
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen()
}