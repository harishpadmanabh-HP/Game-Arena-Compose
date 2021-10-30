package com.example.gamearenacompose.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.data.repositoy.GameRepository
import com.example.gamearenacompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo:GameRepository
):ViewModel() {
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var genres = mutableStateOf<List<GenreList.Result>>(emptyList<GenreList.Result>())

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



}