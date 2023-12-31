package com.chocolate.tic_tac_toe.presentation.screens.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.tic_tac_toe.presentation.screens.composable.ButtonApp
import com.chocolate.tic_tac_toe.presentation.screens.entry.componants.EnterUserNameBox
import com.chocolate.tic_tac_toe.presentation.screens.entry.componants.GameTitle
import com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel.EntryScreenUIState
import com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel.EntryScreenViewModel
import com.chocolate.tic_tac_toe.presentation.screens.lobby.navigateToLobby
import com.chocolate.tic_tac_toe.presentation.theme.TicTacToeTheme


@Composable
fun EntryScreen(
    viewModel: EntryScreenViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.state.collectAsState().value
    EntryContent(
        state,
        onNameChange = viewModel::onNameChange,
        onClickContinue = viewModel::createPlayer,
        onDropDawnIconClick = viewModel::onNameChange
    )

    LaunchedEffect(key1 = state.isPlayerCreated) {
        if (state.isPlayerCreated) {
            navController.navigateToLobby()
            viewModel.clearIsCreated()
        }
    }

}

@Composable
fun EntryContent(
    state: EntryScreenUIState,
    onNameChange: (String) -> Unit,
    onClickContinue: () -> Unit,
    onDropDawnIconClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        GameTitle()

        EnterUserNameBox(
            text = state.playerName,
            previousUserNames = state.playerPreviousNames,
            onNameChange = onNameChange,
            onDropDawnIconClick = onDropDawnIconClick
        )

        ButtonApp(
            text = "Continue",
            onClick = onClickContinue,
            enabled = state.playerName.isNotBlank(),
            isLoading = state.isLoading
        )
    }

}

@Preview
@Composable
fun EntryScreenPreview() {
    TicTacToeTheme {
        EntryContent(
            state = EntryScreenUIState(),
            onNameChange = {},
            onClickContinue = {},
            onDropDawnIconClick = {}
        )
    }
}