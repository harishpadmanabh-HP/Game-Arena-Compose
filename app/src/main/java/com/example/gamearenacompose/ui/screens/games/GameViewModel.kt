package com.example.gamearenacompose.ui.screens.games

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gamearenacompose.data.paging.GamesDataSource
import com.example.gamearenacompose.data.remote.models.games.Game
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.remote.models.games.GameTrailerList
import com.example.gamearenacompose.data.remote.models.games.ScreenshotList
import com.example.gamearenacompose.data.repositoy.GameRepository
import com.example.gamearenacompose.utils.ApiCallStatus
import com.example.gamearenacompose.utils.ApiMapper
import com.example.gamearenacompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.StringReader
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val repo: GameRepository) : ViewModel() {



    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var search = MutableStateFlow<String>("")

    val gameFlow = MutableStateFlow<ApiMapper<Game>>(ApiMapper(ApiCallStatus.EMPTY, null, null))
    val screenshotListFlow =
        MutableStateFlow<ApiMapper<ScreenshotList>>(ApiMapper(ApiCallStatus.EMPTY, null, null))
    val gameTrailersFlow =
        MutableStateFlow<ApiMapper<GameTrailerList>>(ApiMapper(ApiCallStatus.EMPTY, null, null))

    fun getGameDetails(gameId: Int) {
        viewModelScope.launch {
            Timber.e("GameId $gameId")
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
            when (screenshots.status) {
                ApiCallStatus.SUCCESS -> {
                    screenshotListFlow.value =
                        ApiMapper(ApiCallStatus.SUCCESS, screenshots?.data, null)
                }
                ApiCallStatus.ERROR -> {
                    gameFlow.value = ApiMapper(ApiCallStatus.ERROR, null, screenshots.errorMessage)
                }
            }
        }
    }

    fun getPagedGames() = search.flatMapLatest {querry->
        Pager(PagingConfig(1), pagingSourceFactory = {
            GamesDataSource(repo, querry)
        }).flow
    }

    fun getGameTrailers(id: Int) {
        viewModelScope.launch {
            val trailerList = repo.getGameTrailers(id)
            when (trailerList.status) {
                ApiCallStatus.SUCCESS -> {
                    gameTrailersFlow.value =
                        ApiMapper(ApiCallStatus.SUCCESS, trailerList?.data, null)
                }
                ApiCallStatus.ERROR -> {
                    gameTrailersFlow.value =
                        ApiMapper(ApiCallStatus.ERROR, null, trailerList.errorMessage)
                }
            }

        }
    }

}