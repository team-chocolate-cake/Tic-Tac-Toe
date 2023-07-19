package com.chocolate.tic_tac_toe.presentation.screens.game

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.domain.model.GameState
import com.chocolate.tic_tac_toe.presentation.screens.game.components.DrawCard
import com.chocolate.tic_tac_toe.presentation.screens.game.components.ImageForBackground
import com.chocolate.tic_tac_toe.presentation.screens.game.components.LazyVerticalGridDemoContent
import com.chocolate.tic_tac_toe.presentation.screens.game.components.PlayAgainCard
import com.chocolate.tic_tac_toe.presentation.screens.game.components.PlayersContent
import com.chocolate.tic_tac_toe.presentation.screens.game.components.WaitCard
import com.chocolate.tic_tac_toe.presentation.screens.game.components.WinnerCard
import com.chocolate.tic_tac_toe.presentation.screens.game.view_model.GameUiState
import com.chocolate.tic_tac_toe.presentation.screens.game.view_model.GameViewModel
import com.chocolate.tic_tac_toe.presentation.screens.lobby.navigateToLobby
import com.chocolate.tic_tac_toe.presentation.theme.DarkBackground
import com.chocolate.tic_tac_toe.presentation.theme.TicTacToeTheme

@SuppressLint("SuspiciousIndentation")
@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state by viewModel.state.collectAsState()

    GameScreenContent(
        state = state,
        onGameEnded = {
            viewModel.onGameEnded().also { navController.navigateToLobby() }
        },
        onClickBox = viewModel::updateGameState,
        onClickClose = {
            viewModel.onClose().also { navController.navigateToLobby() }
        },
        onClickPlayAgain = viewModel::onPlayAgain,
    )
}

@Composable
fun GameScreenContent(
    state: GameUiState,
    onClickBox: (Int, String) -> Unit,
    onGameEnded: () -> Unit,
    onClickClose: () -> Unit = {},
    onClickPlayAgain: () -> Unit = {},
) {
    Box {
        ImageForBackground()
        Column(
            modifier = Modifier
                .background(color = DarkBackground)
                .systemBarsPadding()
        ) {
            if (state.players.isNotEmpty()) {
                PlayersContent(
                    turn = state.turn,
                    xPLayer = state.players.first(),
                    oPLayer = if (state.players.size > 1) state.players.last() else null,
                    modifier = Modifier.padding(24.dp)
                )
            }

            when (state.gameState) {
                GameState.WAITING_FOR_PLAYERS -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                GameState.IN_PROGRESS -> {
                    LazyVerticalGridDemoContent(
                        state = state,
                        onClickBox = onClickBox,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                GameState.PLAYER_X_WON, GameState.PLAYER_O_WON -> {
                    DrawCard(
                        image = R.drawable.avatar_batman,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onClickCLose = onClickClose,
                        onClickPlayAgain = onClickPlayAgain
                    )
//                    WinnerCard(
//                        player =
//                        if (state.gameState == GameState.PLAYER_X_WON)
//                            state.players.first()
//                        else state.players.last(),
//                        modifier = Modifier.padding(horizontal = 16.dp),
//                        onClickCLose = onClickClose,
//                        onClickPlayAgain = onClickPlayAgain
//                    )
                }
                GameState.WAITING_PLAYER_X,GameState.WAITING_PLAYER_O -> {
                    if (state.playerId == state.players.first().id && state.gameState == GameState.WAITING_PLAYER_O) {

                        WaitCard(text = state.players.last().name)

                    } else if (state.playerId == state.players.last().id && state.gameState == GameState.WAITING_PLAYER_X) {

                        WaitCard(text = state.players.first().name)

                    } else {
                        PlayAgainCard(onClickPlayAgain = onClickPlayAgain)
                    }

                }

                GameState.END -> {
                    onGameEnded()
                }

                else -> {
//                    DrawCard(
//                        image = R.drawable.avatar_batman,
//                        modifier = Modifier.padding(horizontal = 16.dp),
//                        onClickCLose = onClickClose,
//                        onClickPlayAgain = onClickPlayAgain
//                    )
                    WinnerCard(
                        player =
                        if (state.gameState == GameState.PLAYER_X_WON)
                            state.players.first()
                        else state.players.last(),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onClickCLose = onClickClose,
                        onClickPlayAgain = onClickPlayAgain
                    )
                }
            }

        }
    }
}


@Preview
@Composable
fun GameScreenPreview() {
    TicTacToeTheme {
        GameScreen(
            navController = NavController(LocalContext.current)
        )
    }
}