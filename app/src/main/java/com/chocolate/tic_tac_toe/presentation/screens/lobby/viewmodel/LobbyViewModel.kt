package com.chocolate.tic_tac_toe.presentation.screens.lobby.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.tic_tac_toe.domain.usecase.CreateSessionUseCase
import com.chocolate.tic_tac_toe.domain.usecase.lobby.GetPlayerByIdUseCase
import com.chocolate.tic_tac_toe.domain.usecase.lobby.GetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val getPlayerUseCase: GetPlayerByIdUseCase,
    private val getPlayersUseCase: GetPlayersUseCase,
    private val createSessionUseCase: CreateSessionUseCase,
    private val playerUiStateMapper: PlayerUiStateMapper,
) : ViewModel() , LobbyListener{

    private val _state = MutableStateFlow(LobbyUiState())
    val state = _state.asStateFlow()

    init {
        getPlayer(id = ID)
        getPlayers()
    }

    private fun getPlayer(id: String) {
        viewModelScope.launch {
            getPlayerUseCase(id = id).collect { player ->
                _state.update {
                    it.copy(
                        player = playerUiStateMapper.map(player!!),
                        isLoading = false,
                        error = null,
                    )
                }
            }
        }
    }

    private fun getPlayers() {
        viewModelScope.launch {
            getPlayersUseCase().collect { players ->
                _state.update {
                    it.copy(
                        players = players.map { players ->
                            playerUiStateMapper.map(players!!)
                        }.sortedByDescending { player ->
                            player.score
                        },
                        isLoading = false,
                        error = null,
                    )
                }
            }
        }
    }

    override fun onClickPlayer(sessionId: String) {

    }

    override fun onClickCreateSession() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            createSessionUseCase()
            _state.update { it.copy(isLoading = false) }
        }

    }

    override fun navigateToGameScreen(sessionID: String) {

    }

    companion object {
        private const val ID = "1689552385460"
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