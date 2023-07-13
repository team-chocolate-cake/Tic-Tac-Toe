package com.chocolate.tic_tac_toe.presentation.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.chocolate.tic_tac_toe.R

@Composable
fun ImageForBackground() {
    Image(
        painter = painterResource(id = R.drawable.background_game),
        contentDescription = null,
        contentScale = ContentScale.FillBounds, modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    )
}