package com.chocolate.tic_tac_toe.presentation.screens.lobby

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.tic_tac_toe.presentation.screens.composable.ButtonApp
import com.chocolate.tic_tac_toe.presentation.screens.game.navigateToGame
import com.chocolate.tic_tac_toe.presentation.screens.lobby.components.PlayerContent
import com.chocolate.tic_tac_toe.presentation.screens.lobby.components.PlayerContentHeader
import com.chocolate.tic_tac_toe.presentation.screens.lobby.components.TopThreePlayers
import com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel.LobbyUiState
import com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel.LobbyViewModel
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBackground87

@SuppressLint("SuspiciousIndentation")
@Composable
fun LobbyScreen(
    viewModel: LobbyViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LobbyContent(
        state = state,
        onClickCreateSession = viewModel::onClickCreateSession,
        onClickPlayer = { sessionId ->
            if (state.player.id != sessionId) {
                viewModel.onClickPlayer(sessionId)
                navController.navigateToGame(sessionId)
            } else {
                Toast.makeText(context, "Rejoining the game is not allowed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    )

    DisposableEffect(key1 = state.isSessionCreated) {
        if (state.isSessionCreated) {
            navController.navigateToGame(state.player.id)
        }
        onDispose { viewModel.clearIsSessionCreated() }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LobbyContent(
    state: LobbyUiState,
    onClickCreateSession: () -> Unit,
    onClickPlayer: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            item {
                PlayerContentHeader(
                    modifier = Modifier.padding(vertical = 8.dp),
                    player = state.player
                )
            }

            item {
                if (state.players.isNotEmpty()) {
                    TopThreePlayers(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        players = state.players
                    )
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Players",
                    style = MaterialTheme.typography.bodyLarge,
                    color = DarkOnBackground87,
                )
            }

            items(state.players, key = { it.id }) {
                PlayerContent(
                    player = it,
                    onClickPlayer = onClickPlayer,
                    modifier = Modifier
                        .animateItemPlacement()
                        .padding(bottom = 8.dp)
                )
            }
        }

        ButtonApp(
            modifier = Modifier
                .padding(top = 8.dp),
            text = "Create Game",
            onClick = onClickCreateSession,
            isLoading = state.isLoading
        )
    }
}


