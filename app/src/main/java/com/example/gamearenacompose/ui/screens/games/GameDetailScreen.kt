package com.example.gamearenacompose.ui.screens.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.gamearenacompose.R
import com.example.gamearenacompose.data.remote.models.games.Game
import com.example.gamearenacompose.data.remote.models.games.ScreenshotList
import com.example.gamearenacompose.ui.theme.grey
import com.example.gamearenacompose.utils.ApiMapper
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@ExperimentalPagerApi
@Composable
fun GameDetailScreen(
    gameID: Int?,
    navController: NavController,
    viewModel: GameViewModel = hiltViewModel<GameViewModel>()
) {

    if (gameID != null) {
        viewModel.getGameDetails(gameId = gameID)
        viewModel.getGameScreenShots(id = gameID)
    }
    val game by viewModel.gameFlow.collectAsState()
    val screenshots by viewModel.screenshotListFlow.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        game.data?.let {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    GameDetails(game = it, screenshot = screenshots, navController = navController)
                }

            }


        }
    }


}

@ExperimentalPagerApi
@Composable
fun GameDetails(
    game: Game, screenshot: ApiMapper<ScreenshotList>, navController: NavController,
    modifier: Modifier = Modifier
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.LightGray, grey)
                    )
                )
        ) {
            Image(
                painter = rememberImagePainter(game.backgroundImage),
                contentDescription = "Game Image",
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                alpha = .7f
            )
        }

        RatingInfoBar(
            rating = game.ratingTop.toString(),
            genre = game.genres.first().name,
        )

        InfoView(title = game.name, desc = game.descriptionRaw)

        screenshot?.data?.let {
            ScreenShotsView(it,modifier=Modifier.padding(12.dp))
        }


    }
}

@Composable
fun RatingInfoBar(rating: String, genre: String, modifier: Modifier = Modifier) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 16.dp)
    ) {
        Box(
            Modifier
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.DarkGray)
        ) {
            Text(
                text = genre, color = Color.White, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    fontSize = 12.sp
                ), modifier = Modifier.padding(8.dp)
            )

        }
        Box(
            Modifier
                .padding(start = 12.dp)
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.DarkGray)
        ) {
            Row(Modifier.padding(8.dp)) {
                Icon(
                    Icons.Filled.StarRate,
                    "Rating",
                    tint = Color.Yellow,
                    modifier = Modifier
                        .height(12.dp)
                        .width(12.dp)
                )
                Text(
                    text = rating, color = Color.White, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontSize = 12.sp
                    ), modifier = Modifier.padding(start = 8.dp)
                )
            }


        }
    }
}

@Composable
fun InfoView(title: String, desc: String, modifier: Modifier = Modifier) {
    Column(Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)) {
        Text(
            text = title, color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontSize = 18.sp
            ), modifier = Modifier.padding(8.dp)
        )
        Text(
            text = desc, color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 12.sp
            ), modifier = Modifier.padding(8.dp), lineHeight = 25.sp

        )

    }
}

@ExperimentalPagerApi
@Composable
fun ScreenShotsView(screenshotList: ScreenshotList, modifier: Modifier) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "Screenshots", color = Color.White, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontSize = 18.sp
            ), modifier = Modifier.padding(top = 8.dp, start = 25.dp)
        )
        HorizontalPager(
            count = screenshotList.results.size, modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxHeight()

        ) { page ->
            Box(
                modifier = Modifier.padding(12.dp)
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.LightGray, grey)
                        )
                    )){
                Image(
                    painter = rememberImagePainter(screenshotList.results[page].image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )
            }

        }

    }


}

