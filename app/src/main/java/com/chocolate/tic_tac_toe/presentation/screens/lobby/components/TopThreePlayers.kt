package com.chocolate.tic_tac_toe.presentation.screens.lobby.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.screens.composable.PlayerImage
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical4
import com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel.PlayerUiState
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBackground87

@Composable
fun TopThreePlayers(
    players: List<PlayerUiState>,
) {
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
                    PlayerImage(
                        imageUrl = players[PLAYER_TOW_INDEX].imageUrl,
                        size = 88,
                        borderWidth = 2
                    )
                    SpacerVertical4()
                    Text(
                        text = players[PLAYER_TOW_INDEX].name,
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
                    PlayerImage(
                        imageUrl = players[PLAYER_THREE_INDEX].imageUrl,
                        size = 88,
                        borderWidth = 2
                    )
                    SpacerVertical4()
                    Text(
                        text = players[PLAYER_THREE_INDEX].name,
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
                    PlayerImage(
                        imageUrl = players[PLAYER_ONE_INDEX].imageUrl,
                        size = 125,
                        borderWidth = 2
                    )
                    SpacerVertical4()
                    Text(
                        text = players[PLAYER_ONE_INDEX].name,
                        style = MaterialTheme.typography.titleSmall,
                        color = DarkOnBackground87
                    )

                }
            }
        }
    }
}

private const val PLAYER_ONE_INDEX = 0
private const val PLAYER_TOW_INDEX = 1
private const val PLAYER_THREE_INDEX = 2