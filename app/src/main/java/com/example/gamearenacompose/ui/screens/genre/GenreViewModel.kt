package com.example.gamearenacompose.ui.screens.genre

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.gamearenacompose.data.paging.GamesDataSource
import com.example.gamearenacompose.data.paging.GenresDataSource
import com.example.gamearenacompose.data.remote.models.genre.Genre
import com.example.gamearenacompose.data.repositoy.GameRepository
import com.example.gamearenacompose.utils.ApiCallStatus
import com.example.gamearenacompose.utils.ApiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val repo:GameRepository):ViewModel(){

    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var search = MutableStateFlow<String>("")
    var genreDetails = MutableStateFlow<ApiMapper<Genre>>(ApiMapper(ApiCallStatus.EMPTY,null,null))

    fun getPaginatedGenres()=search.flatMapLatest {querry->
        Pager(PagingConfig(1), pagingSourceFactory = {
            GenresDataSource(repo, querry)
        }).flow
    }

    fun getGenreDetails(id:Int){
        viewModelScope.launch {
            val genreResponse = repo.getGenreDetails(id)
            when(genreResponse.status){
                ApiCallStatus.SUCCESS->{
                    genreDetails.value = ApiMapper(ApiCallStatus.SUCCESS,genreResponse.data,null)
                }
                ApiCallStatus.ERROR->{
                    genreDetails.value = ApiMapper(ApiCallStatus.ERROR,null,genreResponse.errorMessage)
                }

            }

        }

    }


}