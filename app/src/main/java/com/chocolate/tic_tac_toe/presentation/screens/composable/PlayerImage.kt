package com.chocolate.tic_tac_toe.presentation.screens.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBorder

@Composable
fun PlayerImage(imageId: Int = R.drawable.player1  , size: Int = 36 ,borderWidth: Int = 1){
    Image(painter = painterResource(id = imageId), contentDescription ="" ,
    modifier = Modifier
        .size(size.dp).fillMaxSize()
        .clip(CircleShape)
        .border(width = borderWidth.dp , color = DarkOnBorder , shape = CircleShape ),
            contentScale = ContentScale.FillBounds,
    )
}