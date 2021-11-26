package com.example.gamearenacompose.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamearenacompose.ui.screens.games.AllGamesScreen
import com.example.gamearenacompose.ui.screens.games.GameDetailScreen
import com.example.gamearenacompose.ui.screens.genre.AllGenresScreen
import com.example.gamearenacompose.ui.screens.genre.GenreDetailsScreen
import com.example.gamearenacompose.ui.screens.home.HomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * Defines the navigation graph and navigation actions for all screen in he app.
 */


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun GameArenaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),   //get nav host controller instance created,this navController is passed to all fns in which further navigation is required
    startDestination: String = GameArenaDestinations.HOME_ROUTE               //starting route (screen)
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        composable(GameArenaDestinations.HOME_ROUTE) {
            HomeScreen(navController = navController)          //Home Screen
        }
        composable(GameArenaDestinations.GENRE_ROUTE) {
            GenreDetailsScreen(                               //Genre Details screen
                genreId = it.arguments?.getString("id")?.toInt(),
                navController = navController
            )
        }
        composable(GameArenaDestinations.ALL_GENRE_ROUTE) {
            AllGenresScreen(navController)                   //All Genres listing Screen
        }
        composable(GameArenaDestinations.ALL_GAMES_ROUTE) {
            AllGamesScreen(navController)                //All games listing Sreen
        }
        composable(GameArenaDestinations.GAME_ROUTE) {
            GameDetailScreen(
                gameID = it.arguments?.getString("id")?.toInt(),  //Game details Screen
                navController = navController
            )
        }

    }


}