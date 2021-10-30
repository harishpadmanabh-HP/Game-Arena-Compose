package com.example.gamearenacompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamearenacompose.ui.screens.home.HomeScreen
import com.example.gamearenacompose.ui.screens.home.HomeViewModel

@Composable
fun GameArenaNavGraph(
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    //openDrawer: () -> Unit = {},
    startDestination: String = GameArenaDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ){
        composable(GameArenaDestinations.HOME_ROUTE){
           HomeScreen()
        }

    }


}