package com.example.gamearenacompose.ui.screens.genre

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun GenreDetailsScreen(
    genreId : Int?,
    navController: NavController
){
    Text(text = "Genre Details ${genreId}")
}