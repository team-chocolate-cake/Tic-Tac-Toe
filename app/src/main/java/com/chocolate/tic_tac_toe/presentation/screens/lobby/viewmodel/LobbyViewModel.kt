package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.domain.model.Player
import com.chocolate.tic_tac_toe.domain.usecase.CreateSessionUseCase
import com.chocolate.tic_tac_toe.domain.usecase.JoinSessionUseCase
import com.chocolate.tic_tac_toe.domain.usecase.lobby.GetPlayerDataUseCase
import com.chocolate.tic_tac_toe.domain.usecase.lobby.GetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val joinSessionUseCase: JoinSessionUseCase,
    private val getPlayerDataUseCase: GetPlayerDataUseCase,
    private val getPlayersUseCase: GetPlayersUseCase,
    private val createSessionUseCase: CreateSessionUseCase,
    private val playerUiStateMapper: PlayerUiStateMapper,
) : ViewModel(), LobbyListener {

    private val _state = MutableStateFlow(LobbyUiState())
    val state = _state.asStateFlow()

    init {
        getPlayerData()
        getPlayers()
    }

    private fun getPlayerData() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getPlayerDataUseCase() },
            onSuccess = ::onGetPlayerDataSuccess,
            onError = ::onGetPlayerDataError
        )
    }

    private fun onGetPlayerDataSuccess(player: Player) {
        _state.update {
            it.copy(
                player = playerUiStateMapper.map(player),
                isLoading = false,
                error = null,
            )
        }
    }

    private fun onGetPlayerDataError(throwable: Throwable) {
        _state.update {
            it.copy(
                isLoading = false,
                error = throwable.message
            )
        }
        Log.i("TAG", "${throwable.message}")
    }

    private fun getPlayers() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getPlayersUseCase() },
            onSuccess = ::onGetPlayersSuccess,
            onError = ::onGetPlayersError
        )
    }

    private fun onGetPlayersError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }

    }

    private fun onGetPlayersSuccess(playersFlow: Flow<List<Player?>>) {
        viewModelScope.launch {
            playersFlow.collect { players ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        players = players.map { player -> playerUiStateMapper.map(player!!) }
                    )
                }
            }
        }

    }


    override fun onClickPlayer(sessionId: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                joinSessionUseCase(
                    sessionId,
                    _state.value.player.id,
                    _state.value.player.name,
                    _state.value.player.imageUrl,
                    _state.value.player.score
                )
            },
            onSuccess = ::onJoinSessionSuccess,
            onError = ::onJoinSessionError
        )
    }

    override fun onClickCreateSession() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                createSessionUseCase(
                    _state.value.player.id,
                    _state.value.player.name,
                    _state.value.player.imageUrl,
                    _state.value.player.score
                )
            },
            onSuccess = ::onCreateSessionSuccess,
            onError = ::onCreateSessionError
        )
    }

    private fun onCreateSessionSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, isSessionCreated = true) }
    }

    fun clearIsSessionCreated() {
        _state.update { it.copy(isSessionCreated = false) }
    }

    private fun onCreateSessionError(throwable: Throwable) {

    }


    private fun onJoinSessionSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, isSessionJoined = true) }
    }

    private fun onJoinSessionError(throwable: Throwable) {

    }

    /*    override fun navigateToGameScreen(sessionID: String) {

        }*/

    private fun <T> tryToExecute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                call().also(onSuccess)
            } catch (throwable: Throwable) {
                onError(throwable)
            }
        }
    }

    fun clearIsSessionJoined() {
        _state.update { it.copy(isSessionJoined = false) }
    }


//    private fun getPlayer1(id: String){
//        _state.update { it.copy(isLoading = true) }
//        tryToExecute(
//            call = {
//                getPlayerUseCase(id)
//            },
//            onSuccess = ::onGetPlayerSuccess,
//            onError = ::onError
//        )
//    }
//
//    private fun onGetPlayerSuccess(item: Player) {
//        _state.update {
//            it.copy(
//                player = item,
//                isLoading = false,
//                error = null,
//            )
//        }
//    }
//
//    private fun getPlayers1(){
//
//    }
//
//
//
//    private fun onError(throwable: Throwable) {
//        if (throwable == NoNetworkThrowable()) {
//            showErrorWithSnackBar(throwable.message ?: "No Network Connection")
//        } else if (throwable == SocketTimeoutException()) {
//            showErrorWithSnackBar(throwable.message ?: "time out!")
//        }
//        _state.update {
//            it.copy(
//                error = throwable.message ?: "No Network Connection",
//                isLoading = false
//            )
//        }
//    }
//
//    private fun showErrorWithSnackBar(messages: String) {
////        sendEvent(MyListDetailsUiEvent.ShowSnackBar(messages))
//    }
//
//    private fun <T> tryToExecute(
//        call: () -> T?,
//        onSuccess: (T?) -> Unit,
//        onError: (Throwable) -> Unit,
//        dispatcher: CoroutineDispatcher = Dispatchers.IO
//    ) {
//        viewModelScope.launch(dispatcher) {
//            try {
//                call().also(onSuccess)
//            } catch (th: Throwable) {
//                onError(th)
//            }
//        }
//    }

}