package com.chocolate.tic_tac_toe.presentation.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkCard
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkOnCard
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkSecondary

@Preview
@Composable
fun LazyVerticalGridDemo() {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(top = 40.dp, start = 16.dp, end = 16.dp)
            .wrapContentSize()
            .background(
                color = DarkCard,
                shape = RoundedCornerShape(size = 16.dp)
            ),
        columns = GridCells.Fixed(count = 3), // adaptive size
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        contentPadding = PaddingValues(all = 12.dp)
    ) {
        items(count = 9) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
                    .background(color = DarkOnCard, shape = RoundedCornerShape(size = 8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$index",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 64.sp
                    ), color = DarkSecondary
                )
            }
        }
    }
}
