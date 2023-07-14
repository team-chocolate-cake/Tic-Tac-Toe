package com.chocolate.tic_tac_toe.presentation.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.unit.sp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkOnSecondary
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacCustomColors
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacToeTheme

@Composable
fun CardWinnerContent(score: Int, type: String, image: Int, colorType: Color) {
    TicTacToeTheme() {
        val color = TicTacCustomColors.current
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .wrapContentSize()
                .clip(RoundedCornerShape(16.dp))
                .background(color.darkCard), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = Modifier.width(149.5.dp)
                    .height(162.75003.dp),
                painter = painterResource(id = image),
                contentDescription = null,
            )
Column( horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly) {
    Text(
        text = "You Win!",
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
        color = color.darkOnBackground87,
        maxLines = 1,

    )
    Text(
        text = "$score Score",
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
        color = color.darkOnBackground87,
        maxLines = 1,
        modifier = Modifier.padding(horizontal = 82.dp)
    )
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(
            text = type,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 64.sp),
            color = colorType,
            maxLines = 1,
            modifier = Modifier.padding(start = 28.dp,end = 182.dp,bottom = 28.dp )

        )
        Text(
            text = type,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 64.sp),
            color = colorType,
            maxLines = 1,
            modifier = Modifier.padding(end =28.dp,bottom = 28.dp )
        )
    }
}


            }
        }

}

@Preview(showSystemUi = true)
@Composable
fun CardWinnerPreview() {
    CardWinnerContent(20,"X",R.drawable.avatar_batman, DarkOnSecondary)

}