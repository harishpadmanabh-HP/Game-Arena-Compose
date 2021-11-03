package com.example.gamearenacompose.ui.screens.games

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.gamearenacompose.R
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.ui.screens.home.SearchBar
import com.example.gamearenacompose.ui.theme.grey

@ExperimentalFoundationApi
@Composable
fun AllGamesScreen(
    navController: NavHostController,
    viewModel: GameViewModel = hiltViewModel<GameViewModel>()
) {
    val lazyGameList = viewModel.getPaginatedGames().collectAsLazyPagingItems()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "All Games", color = Color.White, style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(
                        Font(R.font.roboto_medium)
                    )
                ), modifier = Modifier.padding(16.dp)
            )
            SearchBar(modifier = Modifier.padding(16.dp), hint = "Search Games...") {

            }
            GameListView(lazyGameList)

        }

    }

//    LazyVerticalGrid(
//        cells = GridCells.Fixed(2),
//        content = {
//            items(lazyGameList.itemCount) { index ->
//                lazyGameList[index]?.let {
//                    Text(
//                        modifier = Modifier.fillMaxWidth(),
//                        text = it.name,
//                        color = Color.Black
//                    )
//                }
//            }
//        }
//    )

}

@ExperimentalFoundationApi
@Composable
fun GameListView(lazyGameList: LazyPagingItems<GameList.Result>,
                 modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        content = {
            items(lazyGameList.itemCount) { index ->
                lazyGameList[index]?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it.name,
                        color = Color.Black
                    )
                    GameCardView(it)
                }
            }
        }
    )
}

@Composable
fun GameCardView(game: GameList.Result) {
    Card(
        elevation = 20.dp,
        backgroundColor = grey,
        modifier =
        Modifier.padding(16.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(300.dp)
            .fillMaxWidth()
            .background(
            Brush.verticalGradient(
                listOf(Color.LightGray, grey)
            )
        )
    ) {
        ConstraintLayout {
            val (image, title, rating) = createRefs()
            Image(
                contentScale = ContentScale.Crop,
                painter =
                rememberImagePainter(
                    data = game.backgroundImage,
                    builder = {

                        crossfade(true)
                    }
                ),
                contentDescription = "Image",
                modifier =
                Modifier.constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Text(
                text = game.name,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    fontSize = 12.sp
                ),
                color = Color.White,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                modifier =
                Modifier.constrainAs(title) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Row(
                modifier =
                Modifier.fillMaxWidth().constrainAs(
                    rating
                ) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Text(
                    text = game.rating.toString(),
                    color = Color(0xFFFFC400),
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp
                )

            }
        }
    }

}