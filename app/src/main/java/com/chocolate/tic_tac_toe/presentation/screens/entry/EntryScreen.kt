package com.chocolate.tic_tac_toe.presentation.screens.entry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.screens.entry.componants.EnterYourNameBox
import com.chocolate.tic_tac_toe.presentation.screens.entry.componants.EntryScreenBackground
import com.chocolate.tic_tac_toe.presentation.screens.entry.componants.GameTitle
import com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel.EntryScreenUIState
import com.chocolate.tic_tac_toe.presentation.screens.entry.viewmodel.EntryScreenViewModel
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkPrimary
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacToeTheme


@Composable
fun EntryScreen() {
    val viewModel: EntryScreenViewModel = hiltViewModel()
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

    EntryScreenBackground()

    Column(verticalArrangement = Arrangement.SpaceEvenly) {
        GameTitle()

        EnterYourNameBox(state.playerName, onNameChange = onNameChange)

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xff9c3be4),
                            Color(0xff9d39d3),
                            Color(0xffb82f89)
                        )
                    ), shape = RoundedCornerShape(100.dp)
                ),
            enabled = state.playerName.isNotBlank(),
            onClick = { onClickContinue() }) {
            Text(
                text = "Continue",
                fontFamily = FontFamily(Font(R.font.fingerpaint_regular)),
                fontSize = 24.sp,
                color = Color(0xFFFFFFFF),
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
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