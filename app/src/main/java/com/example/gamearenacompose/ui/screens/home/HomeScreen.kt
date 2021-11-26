package com.example.gamearenacompose.ui.screens.home

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.gamearenacompose.R
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.ui.GameArenaDestinations
import com.example.gamearenacompose.ui.theme.grey
import com.example.gamearenacompose.ui.theme.lightPurple
import com.example.gamearenacompose.ui.theme.purple
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import timber.log.Timber

@ExperimentalMaterialApi
@ExperimentalAnimationApi
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
                // RewardsCard()
                AnimatedRewardCard(viewModel)
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

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun AnimatedRewardCard(viewModel: HomeViewModel) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        color = purple,
        modifier = Modifier
            .padding()
            .shadow(15.dp, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        lightPurple, purple
                    )
                )
            ),
        onClick = { expanded = !expanded }// switch value of expanded state
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 500
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 500
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                RewardCardExpanded(viewModel){
                    expanded = !expanded //collapsing card when callback received after login
                }
            } else {
                RewardsCard()
            }
        }

    }
}

@Composable
fun RewardCardExpanded(viewModel: HomeViewModel,onShrink:()->Unit) {

    var emailInput by remember {
        mutableStateOf("")
    }

    var pswdInput by remember {
        mutableStateOf("")
    }

    var context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(purple)
        ) {
            val (heading, desc, email, pswd, login) = createRefs()
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

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 18.dp, end = 18.dp)
                    .constrainAs(email) {
                        top.linkTo(desc.bottom)
                        start.linkTo(parent.start)
                    },
                value = emailInput,
                onValueChange = {
                    emailInput = it
                },

                label = { Text("Email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = purple,
                    focusedBorderColor = White,
                    unfocusedBorderColor = Black,
                    textColor = Black,
                    focusedLabelColor = Black,
                    unfocusedLabelColor = Black
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                },

                )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 18.dp, end = 18.dp)
                    .constrainAs(pswd) {
                        top.linkTo(email.bottom)
                        start.linkTo(parent.start)
                    },
                value = pswdInput,
                onValueChange = { pswdInput = it },
                label = { Text("Password") },
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = purple,
                    focusedBorderColor = White,
                    unfocusedBorderColor = Black,
                    textColor = Black,
                    focusedLabelColor = Black,
                    unfocusedLabelColor = Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Password,
                        contentDescription = null
                    )
                }
            )

            Button(onClick = { onLoginClicked(emailInput, pswdInput, viewModel){status, message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                onShrink()
            } },
                elevation = ButtonDefaults.elevation(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                modifier = Modifier.constrainAs(login) {
                    end.linkTo(pswd.end, margin = 20.dp)
                    top.linkTo(pswd.bottom)
                    bottom.linkTo(parent.bottom, 20.dp)
                }
            ) {
                Text(
                    text = "LOGIN",
                    color = White,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontSize = 16.sp
                    )
                )
            }
        }

    }
}

fun onLoginClicked(emailInput: String, pswdInput: String, viewModel: HomeViewModel,onLoginCallack:(status:Boolean,message:String)->Unit) {
    Timber.e("Email $emailInput pswd $pswdInput")
    viewModel.doLogin(emailInput, pswdInput){status, message ->
        onLoginCallack(status,message)
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
