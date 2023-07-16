package com.chocolate.tic_tac_toe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.chocolate.tic_tac_toe.presentation.screens.composable.TikTacToeScaffold
import com.chocolate.tic_tac_toe.presentation.screens.composable.TransparentSystemBars
import com.chocolate.tic_tac_toe.presentation.theme.TicTacToeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            TicTacToeTheme {
                TransparentSystemBars()
                TikTacToeScaffold {
                }
            }
        }
    }
}