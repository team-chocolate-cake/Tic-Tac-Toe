package com.chocolate.tic_tac_toe.presentation.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacCustomColors
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacToeTheme

@Composable
fun players() {
    playersContent()
}


@Composable
fun playersContent() {
    TicTacToeTheme() {
        val color = TicTacCustomColors.current
        Row(
            modifier = Modifier
                .height(214.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp), Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .width(118.dp)
                    .height(214.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color.darkCard), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_batman),
                    contentDescription = null,
                    modifier = Modifier
                )

                Text(
                    text = "Abooodex3x",
                    style = MaterialTheme.typography.titleMedium,
                    color = color.darkOnPrimary
                )

                Text(
                    text = "o",
                    color = color.darkSecondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    Text(
                        text = "300",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TicTacCustomColors.current.darkOnPrimary
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

            Text(
                text = "VS", style = MaterialTheme.typography.bodyMedium,
                color = TicTacCustomColors.current.darkSecondary,
                textAlign = TextAlign.Justify, modifier = Modifier.padding(top = 214.dp / 2)
            )


            Column(
                modifier = Modifier
                    .width(118.dp)
                    .height(214.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color.darkCard), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clown),
                    contentDescription = null,
                    modifier = Modifier
                )

                Text(
                    text = "Abooodex3x",
                    style = MaterialTheme.typography.titleMedium,
                    color = color.darkOnPrimary
                )

                Text(
                    text = "o",
                    color = color.darkSecondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    Text(
                        text = "300",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TicTacCustomColors.current.darkOnPrimary
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

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun playersPreview() {
    players()
}