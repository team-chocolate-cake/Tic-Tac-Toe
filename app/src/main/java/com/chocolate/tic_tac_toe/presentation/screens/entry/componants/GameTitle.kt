package com.chocolate.tic_tac_toe.presentation.screens.entry.componants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkOnSecondary
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkPrimary
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkSecondary

@Preview
@Composable
fun GameTitle() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .padding(horizontal = 48.dp)
    ) {
        GameTitleText(
            firstLetter = 'T',
            firstLetterColor = DarkSecondary,
            secondLetter = 'i',
            secondLetterColor = DarkPrimary,
            thirdLetter = 'c',
            thirdLetterColor = DarkOnSecondary,
            Modifier.align(Alignment.Start)
        )
        GameTitleText(
            firstLetter = 'T',
            firstLetterColor = DarkPrimary,
            secondLetter = 'a',
            secondLetterColor = DarkOnSecondary,
            thirdLetter = 'c',
            thirdLetterColor = DarkSecondary,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        )
        GameTitleText(
            firstLetter = 'T',
            firstLetterColor = DarkOnSecondary,
            secondLetter = 'o',
            secondLetterColor = DarkSecondary,
            thirdLetter = 'e',
            thirdLetterColor = DarkPrimary,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp)
        )
    }

}
