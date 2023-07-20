package com.chocolate.tic_tac_toe.presentation.screens.entry.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.chocolate.tic_tac_toe.presentation.theme.TicTacCustomColors

@Composable
fun UserNameDropDownMenu(
    previousUserNames: List<String>,
    onDropDawnIconClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    Text(
        modifier = modifier
            .rotate(-90f)
            .clip(CircleShape)
            .clickable { isContextMenuVisible = true }
            .padding(12.dp),
        text = "<",
        style = MaterialTheme.typography.bodyLarge,
        color = TicTacCustomColors.current.darkOnBackground38
    )
    DropdownMenu(
        modifier = Modifier
            .fillMaxWidth(0.76f)
            .background(color = TicTacCustomColors.current.darkOnCard),
        expanded = isContextMenuVisible,
        onDismissRequest = { isContextMenuVisible = false }
    ) {
        if (previousUserNames.isNotEmpty()) {
            previousUserNames.forEach { name ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = name,
                            color = TicTacCustomColors.current.darkOnBackground60
                        )
                    },
                    onClick = {
                        onDropDawnIconClick(name)
                        isContextMenuVisible = false
                    }
                )
            }
        } else {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "There is no previous user names",
                        color = TicTacCustomColors.current.darkOnBackground60
                    )
                },
                onClick = {
                    isContextMenuVisible = false
                })
        }
    }
}