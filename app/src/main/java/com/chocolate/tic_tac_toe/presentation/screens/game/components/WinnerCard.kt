package com.chocolate.tic_tac_toe.presentation.screens.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.tic_tac_toe.presentation.screens.game.view_model.GameUiState
import com.chocolate.tic_tac_toe.presentation.theme.TicTacCustomColors
import com.chocolate.tic_tac_toe.presentation.theme.TicTacToeTheme

@Composable
fun WinnerCard(
    modifier: Modifier = Modifier,
    player: GameUiState.Player = GameUiState.Player(),
    onClickCLose: () -> Unit,
    onClickPlayAgain: () -> Unit,
) {
    val color = TicTacCustomColors.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color.darkCard).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Image(
            modifier = Modifier
                .width(155.dp)
                .height(168.dp),
            painter = rememberAsyncImagePainter(player.imageUrl),
            contentDescription = null,
        )

        Text(
            text = "${player.name} Won!",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
            color = color.darkOnBackground87,
            maxLines = 1,
        )

        Text(
            text = "+10 Score",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
            color = color.darkOnBackground87,
            maxLines = 1,
            modifier = Modifier
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                onClick =  onClickCLose,
            ) {
                Text(
                    text = "X",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 64.sp),
                    color = color.darkOnSecondary,
                )
            }

            TextButton(
                onClick = onClickPlayAgain,
            ) {
                Text(
                    modifier = Modifier
                        .rotate(60f)
                        .offset(x = 5.dp, y = (-60).dp),
                    text = "<",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 64.sp),
                    color = color.darkSecondary,
                )
                Text(
                    text = "O",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 64.sp),
                    color = color.darkSecondary,
                )
            }
        }
    }
}


@Preview
@Composable
fun CardWinnerPreview() {
    TicTacToeTheme {
        WinnerCard(
            player = GameUiState.Player(
                name = "Player 1",
                imageUrl = "https://cdn.discordapp.com/attachments/812835553522483250/1130838240304312382/avatar_1.png",
            ),
            onClickCLose = {},
            onClickPlayAgain = {}
        )
    }
}