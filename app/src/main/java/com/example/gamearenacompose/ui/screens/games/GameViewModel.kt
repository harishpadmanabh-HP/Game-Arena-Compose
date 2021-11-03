package com.example.gamearenacompose.ui.screens.games

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamearenacompose.data.remote.models.games.Game
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.remote.models.games.ScreenshotList
import com.example.gamearenacompose.data.repositoy.GameRepository
import com.example.gamearenacompose.utils.ApiCallStatus
import com.example.gamearenacompose.utils.ApiMapper
import com.example.gamearenacompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val repo: GameRepository) : ViewModel() {
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")

    val gameFlow = MutableStateFlow<ApiMapper<Game>>(ApiMapper(ApiCallStatus.EMPTY, null, null))
    val screenshotListFlow =
        MutableStateFlow<ApiMapper<ScreenshotList>>(ApiMapper(ApiCallStatus.EMPTY, null, null))

    fun getGameDetails(gameId: Int) {
        viewModelScope.launch {
            val game = repo.getGameDetails(gameId)
            when (game.status) {
                ApiCallStatus.SUCCESS -> {
                    gameFlow.value = ApiMapper(ApiCallStatus.SUCCESS, game?.data, null)
                }
                ApiCallStatus.ERROR -> {
                    gameFlow.value = ApiMapper(ApiCallStatus.ERROR, null, game.errorMessage)
                }
            }
        }
    }

    fun getGameScreenShots(id: Int) {
        viewModelScope.launch {
            val screenshots = repo.getGameScreenShots(id)
            when(screenshots.status){
                ApiCallStatus.SUCCESS->{
                    screenshotListFlow.value = ApiMapper(ApiCallStatus.SUCCESS,screenshots?.data,null)
                }
                ApiCallStatus.ERROR -> {
                    gameFlow.value = ApiMapper(ApiCallStatus.ERROR, null, screenshots.errorMessage)
                }
            }
        }
    }


}