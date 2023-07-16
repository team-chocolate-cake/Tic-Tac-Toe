package com.chocolate.tic_tac_toe.domain.usecase.lobby

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.domain.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) {
     operator fun invoke() : Flow<List<Player?>> {
        return firebasePlayerDatabase.getPlayers()
    }
}