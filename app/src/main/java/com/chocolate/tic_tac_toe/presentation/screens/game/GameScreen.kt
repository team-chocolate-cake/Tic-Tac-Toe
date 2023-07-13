package com.chocolate.tic_tac_toe.presentation.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkBackground

@Composable
fun GameScreen() {
    GameScreenContent()
}


@Preview
@Composable
fun GameScreenContent() {
    Box() {
        ImageForBackground()
        Column(modifier = Modifier.background(color = DarkBackground)) {

            playersContent()
            LazyVerticalGridDemoContent()
        }

    }

}

@Preview
@Composable
fun GameScreenPreview() {

}