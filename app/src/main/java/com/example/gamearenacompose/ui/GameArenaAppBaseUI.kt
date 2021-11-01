package com.example.gamearenacompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gamearenacompose.ui.theme.GameArenaComposeTheme
import com.example.gamearenacompose.utils.WindowSize
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun GameArenaAppUi(
    windowSize: WindowSize
) {
    GameArenaComposeTheme {
        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
        }
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            GameArenaNavigationActions(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: GameArenaDestinations.HOME_ROUTE

        val isExpandedScreen = windowSize == WindowSize.Expanded
        GameArenaNavGraph(isExpandedScreen = isExpandedScreen)



    }


}