package com.chocolate.tic_tac_toe.presentation.screens.game

import androidx.lifecycle.ViewModel
import com.chocolate.tic_tac_toe.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
)  :ViewModel() {

    init {
       updateBoard()
    }

    fun updateBoard(){
        gameRepository.updateBoard(listOf( listOf("X","O","X"),listOf("X","O","X"),listOf("X","O","X")),
            key)
    }



companion object{
   private const val key ="-N_OIaJqsm7zT_Ik3TPp"
}

}