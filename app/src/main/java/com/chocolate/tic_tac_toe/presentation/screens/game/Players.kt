package com.chocolate.tic_tac_toe.presentation.screens.game

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
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacCustomColors
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacToeTheme

@Composable
fun Players() {
    playersContent()
}


@Composable
fun playersContent() {
    TicTacToeTheme() {
        val color = TicTacCustomColors.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 76.dp)
                .padding(horizontal = 16.dp), Arrangement.SpaceEvenly
        ) {
            CardPlayer(
                "aboooodx3x", 300, "O", R.drawable.avatar_batman, color.darkSecondary
            )
            Text(
                text = "VS", style = MaterialTheme.typography.bodyMedium,
                color = TicTacCustomColors.current.darkOnPrimary,
                textAlign = TextAlign.Justify, modifier = Modifier.padding(top = 214.dp / 2)
            )

            CardPlayer(
                "abdallhah3x", 300, "X", R.drawable.clown, color.darkOnSecondary
            )

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun playersPreview() {
    Players()
}