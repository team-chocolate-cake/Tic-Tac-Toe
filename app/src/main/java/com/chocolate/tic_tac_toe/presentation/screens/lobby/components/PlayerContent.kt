package com.chocolate.tic_tac_toe.presentation.screens.lobby.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.screens.composable.PlayerImage
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerHorizontal4
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerHorizontal8
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical4
import com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel.PlayerUiState
import com.chocolate.tic_tac_toe.presentation.theme.DarkCard87
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBackground38
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBackground87
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnCard

@Composable
fun PlayerContent(
    player: PlayerUiState,
    modifier: Modifier = Modifier,
    onClickPlayer: ((String) -> Unit) = {},
) {
    val colorAnimation by animateColorAsState(
        targetValue = if (player.isWaiting) DarkCard87 else DarkOnCard,
        label = "colorAnimation"
    )

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(
                color = colorAnimation,
            )
            .fillMaxWidth()
            .clickable { if (player.isWaiting) onClickPlayer(player.id) }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayerImage(imageUrl = player.imageUrl, size = 42)
            SpacerHorizontal8()
            Text(
                text = player.name,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                color = DarkOnBackground87,
            )
        }

        Row {
            Text(
                text = player.score.toString(),
                modifier = Modifier.padding(end = 4.dp),
                style = MaterialTheme.typography.bodySmall,
                color = DarkOnBackground87,
            )
            SpacerHorizontal4()

            Image(
                painter = painterResource(id = R.drawable.ic_fire),
                contentDescription = "",
            )

        }

    }
}


@Composable
fun PlayerContentHeader(
    player: PlayerUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlayerImage(imageUrl = player.imageUrl)
        SpacerHorizontal8()

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = player.name,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                color = DarkOnBackground87,
            )
            SpacerVertical4()

            Row {
                Text(
                    text = player.score.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkOnBackground38,
                )
                SpacerHorizontal4()

                Image(
                    painter = painterResource(id = R.drawable.ic_fire),
                    contentDescription = "",
                )
            }
        }
    }
}

@Preview
@Composable
fun PlayerContentPreview() {
    PlayerContent(
        player = PlayerUiState(
            id = "1",
            name = "Player 1",
            score = 0,
            imageUrl = "",
            isWaiting = true
        )
    )
}