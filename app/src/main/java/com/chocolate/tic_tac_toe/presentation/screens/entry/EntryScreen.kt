package com.chocolate.tic_tac_toe.presentation.screens.entry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.screens.composable.ButtonApp
import com.chocolate.tic_tac_toe.presentation.screens.entry.componants.EnterYourNameBox
import com.chocolate.tic_tac_toe.presentation.screens.entry.componants.GameTitle
import com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel.EntryScreenUIState
import com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel.EntryScreenViewModel
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
        onClickContinue = viewModel::createPlayer
    )

}

@Composable
fun EntryContent(
    state: EntryScreenUIState,
    onNameChange: (String) -> Unit,
    onClickContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        GameTitle()

        EnterYourNameBox(state.playerName, onNameChange = onNameChange)

        ButtonApp(
            text = "Continue",
            onClick = onClickContinue,
            enabled = state.playerName.isNotBlank(),
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
            onClickContinue = {}
        )
    }

}