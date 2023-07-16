package com.chocolate.tic_tac_toe.presentation.screens.lobby

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.tic_tac_toe.domain.model.Session
import com.chocolate.tic_tac_toe.presentation.screens.composable.ButtonApp
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical12
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical24
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical8
import com.chocolate.tic_tac_toe.presentation.screens.composable.TikTacToeScaffold
import com.chocolate.tic_tac_toe.presentation.screens.lobby.components.PlayerContent
import com.chocolate.tic_tac_toe.presentation.screens.lobby.components.PlayerContentHeader
import com.chocolate.tic_tac_toe.presentation.screens.lobby.components.TopThreePlayers
import com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel.LobbyUiState
import com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel.LobbyViewModel
import com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel.PlayerUiState
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBackground87

@SuppressLint("SuspiciousIndentation")
@Composable
fun LobbyScreen(
    viewModel: LobbyViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LobbyContent(
        state = state,
        onClickCreateSession = viewModel::onClickCreateSession,
        navigateToGameScreen = viewModel::navigateToGameScreen,
    )
}

@Composable
fun LobbyContent(
    state: LobbyUiState,
    onClickCreateSession: (Session) -> Unit,
    navigateToGameScreen: (String) -> Unit,
) {
    TikTacToeScaffold {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .scrollable(
                    state = scrollState, orientation = Orientation.Vertical
                )
        ) {

            if (state.player != null)
            PlayerContentHeader(player = state.player)
            SpacerVertical8()
            TopThreePlayers(players = state.players)
            SpacerVertical24()
            Players(players = state.players , onClickPlayer = navigateToGameScreen)
            SpacerVertical24()
            ButtonApp(text = "Create Game", onClick =  onClickCreateSession )

        }
    }
}


@Composable
private fun Players(
    players: List<PlayerUiState>,
    onClickPlayer: (String) -> Unit
) {
    Text(
        text = "Players",
        style = MaterialTheme.typography.bodyLarge,
        color = DarkOnBackground87,
    )
    SpacerVertical8()
    LazyColumn() {
        items(players) {
            PlayerContent(player = it ,onClickPlayer = onClickPlayer)
            SpacerVertical12()
        }
    }
}