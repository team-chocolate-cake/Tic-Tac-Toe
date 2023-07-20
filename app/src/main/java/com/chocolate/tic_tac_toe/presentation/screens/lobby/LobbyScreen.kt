package com.chocolate.tic_tac_toe.presentation.screens.lobby

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.tic_tac_toe.presentation.screens.composable.ButtonApp
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical12
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical24
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical8
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

        },

        )

    if (state.isSessionCreated) {
        navController.navigateToGame(state.player.id)
        viewModel.clearIsSessionCreated()
    }

//    if (state.isSessionJoined) {
//        viewModel.clearIsSessionJoined()
//    }
}

@Composable
fun LobbyContent(
    state: LobbyUiState,
    onClickCreateSession: () -> Unit,
    onClickPlayer: (String) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 24.dp)
            .scrollable(
                state = scrollState, orientation = Orientation.Vertical
            )
    ) {
        Players(
            state = state,
            onClickPlayer = onClickPlayer,
            onClickCreateSession = onClickCreateSession
        )
    }
}


@Composable
private fun Players(
    state: LobbyUiState,
    onClickPlayer: (String) -> Unit,
    onClickCreateSession: () -> Unit,
) {
    LazyColumn {

        item {
            PlayerContentHeader(player = state.player)
            SpacerVertical8()
        }

        item {
            if (state.players.isNotEmpty()) {
                TopThreePlayers(players = state.players)
            }
            SpacerVertical24()
        }

        item {
            Text(
                text = "Players",
                style = MaterialTheme.typography.bodyLarge,
                color = DarkOnBackground87,
            )
            SpacerVertical8()
        }

        items(state.players) {
            PlayerContent(player = it, onClickPlayer = onClickPlayer)
            SpacerVertical12()
        }

        item {
            SpacerVertical24()
            ButtonApp(
                text = "Create Game",
                onClick = onClickCreateSession,
                isLoading = state.isLoading
            )
        }
    }
}