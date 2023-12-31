package com.chocolate.tic_tac_toe.presentation.screens.game.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolate.tic_tac_toe.presentation.screens.game.view_model.GameUiState
import com.chocolate.tic_tac_toe.presentation.theme.DarkCard
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnCard
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnSecondary
import com.chocolate.tic_tac_toe.presentation.theme.DarkSecondary
import com.chocolate.tic_tac_toe.presentation.theme.TicTacToeTheme


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LazyVerticalGridDemoContent(
    state: GameUiState,
    onClickBox: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier
            .background(
                color = DarkCard,
                shape = RoundedCornerShape(size = 16.dp)
            ),
        columns = GridCells.Fixed(count = 3),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        contentPadding = PaddingValues(all = 12.dp)
    ) {
        itemsIndexed(state.board) { index, item ->

            val targetColor =
                if (state.winPositions.isNotEmpty() && state.winPositions.contains(index)) {
                    if (item == "X") DarkSecondary else DarkOnSecondary
                } else {
                    DarkOnCard
                }

            val currentColor by animateColorAsState(
                targetValue = targetColor, label = "",
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = currentColor,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .clickable {
                        if (state.turn == state.playerId) {
                            if (state.playerId == state.players.first().id) {
                                onClickBox(index, state.players.first().symbol)
                            } else {
                                onClickBox(index, state.players.last().symbol)
                            }
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                AnimatedVisibility(
                    visible = item.isNotBlank(),
                    enter = scaleIn(),
                    exit = scaleOut(),
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 64.sp
                        ),
                        color = if (item == "X") DarkOnSecondary else DarkSecondary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DemoContent() {
    TicTacToeTheme() {
        LazyVerticalGridDemoContent(
            state = GameUiState(
                board = listOf(
                    "X", "O", "X",
                    "O", "X", "O",
                    "X", "O", "X"
                ),
            ),
            onClickBox = { _, _ -> }
        )
    }
}

