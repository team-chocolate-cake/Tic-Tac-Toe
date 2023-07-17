package com.chocolate.tic_tac_toe.presentation.screens.entry.componants

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.chocolate.tic_tac_toe.R

@Composable
fun EntryScreenBackground() {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.entry_screen_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}