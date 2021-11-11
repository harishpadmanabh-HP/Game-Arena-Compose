package com.example.gamearenacompose.ui.screens.genre

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.gamearenacompose.data.paging.GamesDataSource
import com.example.gamearenacompose.data.paging.GenresDataSource
import com.example.gamearenacompose.data.repositoy.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val repo:GameRepository):ViewModel(){

    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    var search = MutableStateFlow<String>("")

    fun getPaginatedGenres()=search.flatMapLatest {querry->
        Pager(PagingConfig(1), pagingSourceFactory = {
            GenresDataSource(repo, querry)
        }).flow
    }


}