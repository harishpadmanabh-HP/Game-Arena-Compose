package com.example.gamearenacompose.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gamearenacompose.ui.theme.GameArenaComposeTheme
import com.example.gamearenacompose.ui.theme.darkBLue
import com.example.gamearenacompose.utils.WindowSize
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController


/**
 * This Composable Function is the starting point which has all other screens and configs
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun GameArenaAppUi(
    windowSize: WindowSize
) {
    GameArenaComposeTheme {                   //Applies defined theme to all composable defined in the block
        /**
         * Set System ui bar colors (nav bars anf status bar)
         */
        val systemUiController = rememberSystemUiController()
        val darkIcons = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setSystemBarsColor(darkBLue, darkIcons = darkIcons)  //set colors to system bar and its icons
        }


        /**
         * Set Navigation Controller for the entire app with ONLY ONE INSTANCE of navController
         */
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            GameArenaNavigationActions(navController)      //Configure Navigation graph and Actions
        }


        /**
         * Set up Nav graph for the app
         */
        GameArenaNavGraph()


    }


}