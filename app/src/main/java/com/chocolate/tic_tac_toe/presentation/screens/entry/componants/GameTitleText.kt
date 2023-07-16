package com.chocolate.tic_tac_toe.presentation.screens.entry.componants

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.chocolate.tic_tac_toe.R

@Composable
fun GameTitleText(
    firstLetter: Char,
    firstLetterColor: Color,
    secondLetter: Char,
    secondLetterColor: Color,
    thirdLetter: Char,
    thirdLetterColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = firstLetterColor,
                    fontFamily = FontFamily(Font(R.font.fingerpaint_regular)),
                    fontSize = 100.sp
                )
            ) { append(firstLetter) }
            withStyle(
                style = SpanStyle(
                    color = secondLetterColor,
                    fontFamily = FontFamily(Font(R.font.fingerpaint_regular)),
                    fontSize = 100.sp
                )
            ) { append(secondLetter) }
            withStyle(
                style = SpanStyle(
                    color = thirdLetterColor,
                    fontFamily = FontFamily(Font(R.font.fingerpaint_regular)),
                    fontSize = 100.sp
                )
            ) { append(thirdLetter) }
        },
        modifier = modifier
    )
}
