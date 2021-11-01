package com.example.gamearenacompose.ui.screens.games

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun GameDetailScreen(
    gameID:Int?,
    navController: NavController
){
    Text(text = "Game Details $gameID")
}