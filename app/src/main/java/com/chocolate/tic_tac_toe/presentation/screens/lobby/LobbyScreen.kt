package com.chocolate.tic_tac_toe.presentation.screens.lobby

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.screens.composable.ButtonApp
import com.chocolate.tic_tac_toe.presentation.screens.composable.PlayerImage
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerHorizontal4
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerHorizontal8
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical12
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical24
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical4
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical8
import com.chocolate.tic_tac_toe.presentation.screens.composable.TikTakToeScaffold
import com.chocolate.tic_tac_toe.presentation.theme.DarkCard87
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBackground38
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBackground87
import com.chocolate.tic_tac_toe.presentation.theme.fingerPaintFamily

@Preview(showSystemUi = true)
@Composable
fun LobbyScreen() {
    LobbyContent()
}

@Composable
fun LobbyContent() {
    TikTakToeScaffold {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .scrollable(state= scrollState ,orientation = Orientation.Vertical)
        ) {
            Header(playerName = "moon")
            SpacerVertical24()
            TopThreePlayers()
            SpacerVertical24()
            Players()
            SpacerVertical24()
            ButtonApp(text = "Create Game", onClick = { })

        }
    }
}


@Composable
private fun TopThreePlayers() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        ) {

            val (player1, player2, player3, win) = createRefs()

            Box(
                modifier = Modifier.constrainAs(player2) {
                    end.linkTo(player1.start, margin = (-16).dp)
                    bottom.linkTo(player1.bottom, margin = (-16).dp)
                },
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlayerImage(size = 88, borderWidth = 2)
                    SpacerVertical4()
                    Text(
                        text = "kno",
                        style = MaterialTheme.typography.titleSmall,
                        color = DarkOnBackground87
                    )

                }
            }

            Box(
                modifier = Modifier.constrainAs(player3) {
                    start.linkTo(player1.end, margin = (-16).dp)
                    bottom.linkTo(player1.bottom, margin = (-16).dp)
                },
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlayerImage(size = 88, borderWidth = 2)
                    SpacerVertical4()
                    Text(
                        text = "jhon",
                        style = MaterialTheme.typography.titleSmall,
                        color = DarkOnBackground87
                    )
                }
            }


            Image(painter = painterResource(id = R.drawable.ic_win),
                contentDescription = "",
                modifier = Modifier.constrainAs(win) {
                    end.linkTo(player1.end)
                    start.linkTo(player1.start)
                    top.linkTo(parent.top)
                })

            Box(
                modifier = Modifier.constrainAs(player1) {
                    top.linkTo(win.bottom, margin = (-12).dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlayerImage(size = 125, borderWidth = 2)
                    SpacerVertical4()
                    Text(
                        text = "moon",
                        style = MaterialTheme.typography.titleSmall,
                        color = DarkOnBackground87
                    )

                }
            }
        }
    }
}

@Composable
private fun Players() {
    Text(
        text = "Players",
        style = MaterialTheme.typography.bodyLarge,
        color = DarkOnBackground87,
    )
    SpacerVertical8()
    LazyColumn() {
        items(5) {

            PlayerContent()
            SpacerVertical12()
        }
    }
}


@Composable
private fun PlayerContent(
    playerName: String = "moon",
    playerImage: Int = 0,
    playerScore: Int = 300,
    onClickPlayer: (()-> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .clickable { onClickPlayer }
            .background(color = DarkCard87, shape = CircleShape)
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayerImage(size = 42)
            SpacerHorizontal8()
            Text(
                text = playerName,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                color = DarkOnBackground87,
            )
        }

        Row {
            Text(
                text = playerScore.toString(),
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
fun Header(playerName: String) {
    Row() {


    }
}


@Composable
fun PlayerDetails(name: String) {
    Row() {
        Text(
            text = "player name $name",
            modifier = Modifier.padding(start = 8.dp),
            style = TextStyle(
                color = DarkOnBackground87, fontSize = 14.sp, fontFamily = fingerPaintFamily
            )
        )
    }
    Row() {
        Text(
            text = "player name $name",
            modifier = Modifier.padding(start = 8.dp),
            style = TextStyle(
                color = DarkOnBackground38, fontSize = 14.sp, fontFamily = fingerPaintFamily
            )
        )

    }

}