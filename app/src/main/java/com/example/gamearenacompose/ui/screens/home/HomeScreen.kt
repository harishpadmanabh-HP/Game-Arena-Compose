package com.example.gamearenacompose.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
){
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = viewModel.testViewmodel)


    }
}