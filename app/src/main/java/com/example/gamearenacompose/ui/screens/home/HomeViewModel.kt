package com.example.gamearenacompose.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.gamearenacompose.data.repositoy.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo:GameRepository
):ViewModel() {

    val testViewmodel = "ViewModel Got"

}