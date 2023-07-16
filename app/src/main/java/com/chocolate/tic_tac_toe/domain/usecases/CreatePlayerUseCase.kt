package com.chocolate.tic_tac_toe.domain.usecases

import com.chocolate.tic_tac_toe.data.repository.GameRepository
import com.chocolate.tic_tac_toe.domain.model.Player
import javax.inject.Inject

class CreatePlayerUseCase @Inject constructor(private val gameRepository: GameRepository) {


    operator fun invoke(name: String) {
//        if () {
//            UPDATE
//        }
//        else{
            val id = gameRepository.updatePlayerName(name = name)
//        save id in shared preference
//        }
    }
}