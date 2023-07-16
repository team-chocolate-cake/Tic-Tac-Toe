package com.chocolate.tic_tac_toe.presentation.screens.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.chocolate.tic_tac_toe.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun TikTakToeScaffold(
    content: @Composable (paddingValue: PaddingValues) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background_game),
            contentDescription ="",
            contentScale = ContentScale.FillBounds
        )
        content(paddingValue = PaddingValues(20.dp))
    }
}