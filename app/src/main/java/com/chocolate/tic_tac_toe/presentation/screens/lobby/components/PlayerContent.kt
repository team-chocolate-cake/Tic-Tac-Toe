package com.chocolate.tic_tac_toe.presentation.screens.lobby.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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

@Composable
fun PlayerContent(
    player: PlayerUiState,
    onClickPlayer: ((String) -> Unit) = {},
) {
    Row(
        modifier = Modifier
            .clickable { onClickPlayer(player.createdSessionId) }
            .background(color = DarkCard87, shape = CircleShape)
            .fillMaxWidth()
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
) {
    Row(
        modifier = Modifier
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