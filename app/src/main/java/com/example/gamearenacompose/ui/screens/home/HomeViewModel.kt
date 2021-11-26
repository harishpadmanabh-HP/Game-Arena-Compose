package com.example.gamearenacompose.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamearenacompose.data.Prefs
import com.example.gamearenacompose.data.remote.models.User
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.data.repositoy.GameRepository
import com.example.gamearenacompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo:GameRepository,
    private val prefs:Prefs
):ViewModel() {
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var genres = mutableStateOf<List<GenreList.Result>>(emptyList<GenreList.Result>())
    var games = mutableStateOf<List<GameList.Result>>(emptyList())

    fun getGenres(){
        viewModelScope.launch {
          isLoading.value = true
          val result =  repo.getGenres()
            when(result){
                is Resource.Success ->{
                    Timber.e("Api call success ${result.data}")
                    genres.value = result.data?.results ?: emptyList()
                    isLoading.value = false

                }
                is Resource.Error ->{
                    Timber.e("Api call error ${result.message}")
                    error.value = result.message.toString() ?: ""
                    isLoading.value = false
                }

            }

        }
    }
    fun getGames(){
        viewModelScope.launch {
            isLoading.value = true
            val result = repo.getAllGames()
            when(result){
                is Resource.Success->{
                    games.value = result.data?.results ?: emptyList()
                    isLoading.value = false
                }
                is Resource.Error->{
                    error.value = result.message.toString() ?: ""
                    isLoading.value = false
                }
            }
        }
    }

    fun doLogin(email:String,pswd:String){
        Timber.e("Current user ${ prefs.user}")
        prefs.user = User(email,pswd)
    }



}