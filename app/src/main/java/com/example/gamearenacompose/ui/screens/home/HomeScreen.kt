package com.example.gamearenacompose.ui.screens.home

import android.icu.number.Scale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.gamearenacompose.R
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.ui.GameArenaDestinations
import com.example.gamearenacompose.ui.GameArenaNavigationActions
import com.example.gamearenacompose.ui.theme.darkBLue
import com.example.gamearenacompose.ui.theme.grey
import com.example.gamearenacompose.ui.theme.lightPurple
import com.example.gamearenacompose.ui.theme.purple
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
) {
    viewModel.getGenres()
    val genres by remember {
        viewModel.genres
    }
    viewModel.getGames()
    val games by remember {
        viewModel.games
    }
    val isLoading by remember {
        viewModel.isLoading
    }

    val error by remember {
        viewModel.error
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                HeaderViews()
            }
            if (!genres?.isEmpty()) {
                item {
                    GenreView(
                        genres,
                        isLoading,
                        error,
                        Modifier.layoutId("genre_view"),
                        navController = navController
                    )
                }
            }
            item {
                RewardsCard()
            }
            if (games.isNotEmpty()) {
                item {
                    AllGamesView(
                        games = games,
                        modifier = Modifier,
                        navController = navController
                    )
                }
            }
        }
    }

}

@Composable
fun HeaderViews(modifier: Modifier = Modifier) {
    Column() {
        Text(
            text = "Welcome To",
            color = Color.Gray,
            style = TextStyle(
                fontSize = 14.sp,
            ),
            modifier = Modifier
                .layoutId("txt_welcome")
                .padding(start = 10.dp)
        )
        Text(
            text = "Game Arena",
            color = Color.White,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold))
            ),
            modifier = Modifier
                .layoutId("txt_gme_arena")
                .padding(start = 10.dp)
        )
        SearchBar(
            hint = "Search games...",
            modifier = Modifier
                .layoutId("search")
                .padding(top = 16.dp)
        )
    }
}

//Genre Frame
//https://google.github.io/accompanist/pager/
@ExperimentalPagerApi
@Composable
fun GenreView(
    genres: List<GenreList.Result>,
    isLoading: Boolean,
    error: String,
    modifier: Modifier,
    navController: NavController

) {
    Column {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Genres",
                color = Color.White,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(
                        Font(R.font.roboto_medium)
                    )
                ), modifier = Modifier
                    .layoutId("txt_genre")
                    .padding(top = 12.dp, start = 12.dp)
                    .wrapContentSize(TopStart)
            )
            Text(
                text = "View All  >",
                color = Color.Gray,
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_regular)
                    )
                ), modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("txt_genre")
                    .padding(top = 15.dp, start = 12.dp)
                    .clickable {
                        navController.navigate(GameArenaDestinations.ALL_GENRE_ROUTE)
                    }
            )
        }


        HorizontalPager(
            count = genres.size, modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxHeight(.35f)
        ) { page ->
            GenreViewItem(genre = genres[page], navController = navController)
        }

    }

}

//Genre list item
@ExperimentalCoilApi
@Composable
fun GenreViewItem(
    genre: GenreList.Result,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(top = 2.dp, end = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(Color.LightGray, grey)
                    )
                )
                .clickable {
                    navController.navigate(
                        GameArenaDestinations.GENRE_ROUTE.replace(
                            "{id}",
                            genre.id.toString()
                        )
                    )
                }
        ) {
            Image(
                painter = rememberImagePainter(genre.imageBackground),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = genre.name,
            color = Color.Gray,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 12.dp),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontSize = 16.sp
            )

        )
    }
}


//SearchView
@Composable
fun SearchBar(  //search view
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text, onValueChange = {
                text = it
                onSearch(text)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(grey)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)

            )
        }
    }
}

@Preview
@Composable
fun RewardsCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 14.dp)
            .shadow(15.dp, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        lightPurple, purple
                    )
                )
            )
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (rewardImg, heading, desc) = createRefs()
            Text(
                modifier = Modifier
                    .wrapContentSize(TopStart)
                    .constrainAs(heading) {
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)

                    },
                text = "Get your Rewards!",
                color = Color.White,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold))
                )
            )
            Image(
                painterResource(id = R.drawable.rewards),
                contentDescription = "Rewards",
                modifier = Modifier
                    .padding(end = 12.dp)
                    .width(120.dp)
                    .height(120.dp)
                    .constrainAs(rewardImg) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                modifier = Modifier
                    .padding(top = 12.dp, start = 18.dp)
                    .constrainAs(desc) {
                        top.linkTo(heading.bottom)
                        start.linkTo(parent.start)
                    },
                text = "Claim daily login rewads.",
                color = Color.White,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                )
            )


        }

    }

}

@Composable
fun AllGamesView(
    games: List<GameList.Result>,

    modifier: Modifier,
    navController: NavController
) {
    Column {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Games",
                color = Color.White,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(
                        Font(R.font.roboto_medium)
                    )
                ), modifier = Modifier
                    .layoutId("txt_genre")
                    .padding(top = 12.dp, start = 12.dp)
                    .wrapContentSize(TopStart)
            )
            Text(
                text = "View All  >",
                color = Color.Gray,
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_regular)
                    )
                ), modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("txt_genre")
                    .padding(top = 15.dp, start = 12.dp)
                    .clickable {
                        navController.navigate(GameArenaDestinations.ALL_GAMES_ROUTE)
                    }
            )
        }
        for (i in games.indices step 2) {
            Row(Modifier.fillMaxWidth()) {
                GameItem(
                    game = games[i], navController = navController
                )
                GameItem(game = games[i + 1], navController = navController)


            }

        }
    }

}


@Composable
fun GameItem(
    game: GameList.Result,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(modifier = Modifier.clickable {
        navController.navigate(GameArenaDestinations.GAME_ROUTE.replace("{id}", game.id.toString()))
    }) {
        Box(
            modifier = Modifier
                .padding(top = 12.dp, end = 10.dp)
                .height(200.dp)
                .width(180.dp)
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(Color.LightGray, grey)
                    )
                )
        ) {
            Image(
                painter = rememberImagePainter(game.backgroundImage),
                contentDescription = "Game Image",
                modifier = Modifier
                    .fillMaxSize(),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )

        }
        Text(
            text = game.name,
            color = Color.White,
            modifier = Modifier
                .padding(top = 4.dp, start = 10.dp)
                .width(180.dp),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontSize = 12.sp
            )

        )

    }
}