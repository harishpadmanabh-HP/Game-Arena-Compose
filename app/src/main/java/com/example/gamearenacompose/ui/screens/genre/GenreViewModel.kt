package com.example.gamearenacompose.ui.screens.genre

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gamearenacompose.data.repositoy.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val repo:GameRepository):ViewModel(){

    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")
    
}