package com.chocolate.tic_tac_toe.presentation.screens.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacCustomColors
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacToeTheme

@Composable
fun CardPlayer(name: String, score: Int, type: String, image: Int, colorType: Color) {
    CardPlayerContent(name, score, type, image, colorType)
}

@Composable
fun CardPlayerContent(name: String, score: Int, type: String, image: Int, colorType: Color) {
    TicTacToeTheme() {
        val color = TicTacCustomColors.current
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .width(118.dp)
                .height(214.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color.darkCard), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
            )

            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = color.darkOnBackground87,
                maxLines = 1,
                modifier = Modifier.padding(horizontal = 12.dp)

            )

            Text(
                text = type,
                color = colorType,
                style = MaterialTheme.typography.titleLarge
            )
            Row {
                Text(
                    text = score.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = TicTacCustomColors.current.darkOnBackground87
                )
                Spacer(modifier = Modifier.height(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.fire),
                    contentDescription = "icon fire",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 4.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CardPlayerPreview() {

}