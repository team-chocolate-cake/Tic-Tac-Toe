package com.chocolate.tic_tac_toe.presentation.screens.entry.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkCard
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkOnBackground87
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacCustomColors
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacToeTheme


@Composable
fun EnterYourNameBox(text: String, onNameChange: (String) -> Unit) {

    val colors = TicTacCustomColors.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .background(color = colors.darkCard.copy(alpha = 0.6f), shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = stringResource(R.string.your_name),
                fontFamily = FontFamily(Font(R.font.fingerpaint_regular)),
                fontSize = 24.sp,
                color = colors.darkOnBackground87,
            )
            UserNameRow(text = text, onNameChange = onNameChange)
        }
    }
}

@Preview
@Composable
fun EnterYourNameBoxPreview() {
    TicTacToeTheme {
        EnterYourNameBox(text = "Player 1", onNameChange = {})
    }
}