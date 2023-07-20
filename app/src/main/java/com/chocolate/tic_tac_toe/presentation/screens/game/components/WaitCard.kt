package com.chocolate.tic_tac_toe.presentation.screens.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical12
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical16
import com.chocolate.tic_tac_toe.presentation.theme.TicTacCustomColors

@Composable
fun WaitCard(
    text: String,
    modifier: Modifier = Modifier,
) {
    val color = TicTacCustomColors.current

    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(modifier = Modifier.size(200.dp), colors = CardDefaults.cardColors(color.darkCard)) {
            SpacerVertical12()
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Wait $text",
                color = color.darkOnBackground87,
                textAlign = TextAlign.Center
            )
            SpacerVertical16()
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp), color = color.darkBackground
            )
        }
    }

}
