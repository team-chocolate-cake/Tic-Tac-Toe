package com.chocolate.tic_tac_toe.presentation.screens.game.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.screens.game.view_model.GameUiState
import com.chocolate.tic_tac_toe.presentation.theme.TicTacCustomColors
import com.chocolate.tic_tac_toe.presentation.theme.TicTacToeTheme

@Composable
fun PlayersContent(
    turn: String,
    xPLayer: GameUiState.Player,
    modifier: Modifier = Modifier,
    oPLayer: GameUiState.Player? = null,
) {
    val color = TicTacCustomColors.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardPlayer(
            player = xPLayer,
            colorType = color.darkOnSecondary,
            border = if (turn == xPLayer.id) BorderStroke(2.dp, color.darkOnBorder) else null,
        )

        if (oPLayer != null) {
            Text(
                text = "VS", style = MaterialTheme.typography.bodyMedium,
                color = TicTacCustomColors.current.darkOnPrimary,
                textAlign = TextAlign.Justify, modifier = Modifier.padding(top = 214.dp / 2)
            )

            CardPlayer(
                player = oPLayer,
                colorType = color.darkSecondary,
                border = if (turn == oPLayer.id) BorderStroke(2.dp, color.darkOnBorder) else null,
            )
        }

    }
}

@Preview
@Composable
fun PlayersPreview() {
    TicTacToeTheme {
        PlayersContent(
            xPLayer = GameUiState.Player(
                name = "aboooodx3x",
                score = 300
            ),
            oPLayer = GameUiState.Player(
                name = "aboooodx3x",
                score = 300
            ),
            turn = "X",
        )
    }
}