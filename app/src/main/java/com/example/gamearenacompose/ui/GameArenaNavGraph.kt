package com.example.gamearenacompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamearenacompose.ui.screens.games.AllGamesScreen
import com.example.gamearenacompose.ui.screens.games.GameDetailSreen
import com.example.gamearenacompose.ui.screens.genre.AllGenresScreen
import com.example.gamearenacompose.ui.screens.genre.GenreDetailsScreen
import com.example.gamearenacompose.ui.screens.home.HomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalFoundationApi
@ExperimentalPagerApi
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
    ) {
        composable(GameArenaDestinations.HOME_ROUTE) {
            HomeScreen(navController = navController)
        }
        composable(GameArenaDestinations.GENRE_ROUTE) {
            GenreDetailsScreen(
                genreId = it.arguments?.getString("id")?.toInt(),
                navController = navController
            )
        }
        composable(GameArenaDestinations.ALL_GENRE_ROUTE){
            AllGenresScreen(navController)
        }
        composable(GameArenaDestinations.ALL_GAMES_ROUTE){
            AllGamesScreen(navController)
        }
        composable(GameArenaDestinations.GAME_ROUTE){
            GameDetailSreen(gameID =  it.arguments?.getString("id")?.toInt(), navController =navController )
        }

    }


}