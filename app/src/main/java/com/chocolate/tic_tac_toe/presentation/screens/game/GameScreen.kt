package com.chocolate.tic_tac_toe.presentation.screens.game

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.tic_tac_toe.presentation.screens.game.components.ImageForBackground
import com.chocolate.tic_tac_toe.presentation.screens.game.components.LazyVerticalGridDemoContent
import com.chocolate.tic_tac_toe.presentation.screens.game.components.PlayersContent
import com.chocolate.tic_tac_toe.presentation.screens.game.view_model.GameUiState
import com.chocolate.tic_tac_toe.presentation.screens.game.view_model.GameViewModel
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkBackground
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacToeTheme

@SuppressLint("SuspiciousIndentation")
@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    GameScreenContent(
        state = state,
        onClickBox = viewModel::updateBoard
    )
}

@Composable
fun GameScreenContent(
    state: GameUiState,
    onClickBox: (Int) -> Unit
) {
    Box {
        ImageForBackground()
        Column(modifier = Modifier.background(color = DarkBackground)) {
            PlayersContent(
                turn = state.turn,
                xPLayer = state.xPlayers,
                oPLayer = state.oPlayers,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
            )
            LazyVerticalGridDemoContent(
                state = state,
                onClickBox = { index ->
                    onClickBox(index)
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    TicTacToeTheme {
        GameScreen()
    }
}