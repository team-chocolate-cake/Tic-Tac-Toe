package com.chocolate.tic_tac_toe.presentation.screens.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical16
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical64
import com.chocolate.tic_tac_toe.presentation.theme.TicTacCustomColors
import com.chocolate.tic_tac_toe.presentation.theme.TicTacToeTheme

@Composable
fun PlayAgainCard(
    modifier: Modifier = Modifier, onClickPlayAgain: () -> Unit
) {
    val color = TicTacCustomColors.current

    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier.size(200.dp)
                .clickable { onClickPlayAgain() },
            colors = CardDefaults.cardColors(color.darkCard)
        ) {
            SpacerVertical64()
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Play again with the same player",
                color = color.darkOnBackground87,
                textAlign = TextAlign.Center
            )

            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.same_player),
                contentDescription = "icon wait",


            )

        }
    }

}

@Preview
@Composable
fun GameScreenPreview() {
    TicTacToeTheme {
        PlayAgainCard() {

        }
    }
}