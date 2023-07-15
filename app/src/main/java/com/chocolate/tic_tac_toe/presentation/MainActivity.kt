package com.chocolate.tic_tac_toe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chocolate.tic_tac_toe.presentation.screens.game.GameScreen
import com.chocolate.tic_tac_toe.presentation.ui.theme.TicTacToeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                GameScreen()
            }
        }
    }
}