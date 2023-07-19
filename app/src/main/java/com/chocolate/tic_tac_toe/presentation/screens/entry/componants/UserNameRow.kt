package com.chocolate.tic_tac_toe.presentation.screens.entry.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnCard
import com.chocolate.tic_tac_toe.presentation.theme.TicTacCustomColors

@Composable
fun UserNameRow(
    text: String,
    previousUserNames: List<String>,
    onNameChange: (String) -> Unit,
    onDropDawnIconClick: (String) -> Unit
) {


    Row(
        Modifier
            .padding(24.dp)
            .background(color = DarkOnCard, shape = RoundedCornerShape(100.dp))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text,
            onValueChange = onNameChange,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(0.8f),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = TicTacCustomColors.current.darkOnBackground60
            ),
            singleLine = true
        )
        UserNameDropDownMenu(previousUserNames = previousUserNames, onDropDawnIconClick = onDropDawnIconClick)
    }
}