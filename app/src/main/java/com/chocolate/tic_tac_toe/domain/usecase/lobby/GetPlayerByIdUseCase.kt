package com.chocolate.tic_tac_toe.domain.usecase.lobby

import com.chocolate.tic_tac_toe.data.remote.FirebasePlayerDatabase
import com.chocolate.tic_tac_toe.domain.model.Player
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayerByIdUseCase @Inject constructor(
    private val firebasePlayerDatabase: FirebasePlayerDatabase
) {
    suspend operator fun invoke(id: String ) : Flow<Player?> {
        return firebasePlayerDatabase.getPlayerById(id)
    }
}