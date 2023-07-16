package com.chocolate.tic_tac_toe.presentation.screens.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacCustomColors
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacToeTheme

@Composable
fun DrawCard(
    image: Int,
    modifier: Modifier = Modifier,
) {
    val color = TicTacCustomColors.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color.darkCard)
            .padding(vertical = 24.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Image(
            modifier = Modifier
                .width(155.dp)
                .height(168.dp),
            painter = painterResource(id = image),
            contentDescription = null,
        )
        Text(
            text = "Draw Game!",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
            color = color.darkOnBackground87,
            maxLines = 1,

            )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "X",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 64.sp),
                color = color.darkOnSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "O",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 64.sp),
                color = color.darkSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    TicTacToeTheme() {
        DrawCard(
            image = R.drawable.avatar_batman,
        )
    }
}