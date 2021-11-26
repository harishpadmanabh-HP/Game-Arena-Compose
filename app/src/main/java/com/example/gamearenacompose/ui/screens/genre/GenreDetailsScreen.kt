package com.example.gamearenacompose.ui.screens.genre

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transition.Transition
import com.example.gamearenacompose.R
import dev.chrisbanes.accompanist.coil.CoilImage

//https://proandroiddev.com/animating-the-compose-galaxy-cfaea9dd8d09

@ExperimentalAnimationApi
@Composable
fun GenreDetailsScreen(
    genreId: Int?,
    viewModel: GenreViewModel = hiltViewModel<GenreViewModel>(),
    navController: NavController
) {
    if (genreId != null) {
        viewModel.getGenreDetails(genreId)
    }

    val genre by viewModel.genreDetails.collectAsState()

    // Initially the alpha value will be 0
    val animationTargetState = remember { mutableStateOf(0f) }

    val animatedFloatState = animateFloatAsState(
        // Whenever the target value changes, new animation
        // will start to the new target value
        targetValue = animationTargetState.value,
        animationSpec = tween(durationMillis = 2000)
    )
    Surface(modifier = Modifier.fillMaxSize()) {
    genre.data?.let {details->
            Column(modifier = Modifier.fillMaxSize(1f)) {
                Box(
                    modifier = Modifier
                        .shadow(10.dp, RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
                        .clip(RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
                        .background(Color.DarkGray)
                        .fillMaxWidth(1f)    //setting width with 100% of screen
                        .fillMaxHeight(.5f) //setting height with 50% of screen
                ) {
                    animationTargetState.value =
                        1f // start animtion by change float and pass it to alpha
                    Image(
                        painter = rememberImagePainter(details.imageBackground),
                        contentDescription = "Game Image",
                        modifier = Modifier.fillMaxSize(1f),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        alpha = animatedFloatState.value
                    )

                }
                Text(
                    text = details.name, color = Color.White, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Games in ${details.name} Genre : ${details.gamesCount}", color = Color.White, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontSize = 16.sp
                    ), modifier = Modifier.padding(8.dp), lineHeight = 18.sp
                )
                Text(
                    text = details.description, color = Color.White, style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontSize = 16.sp
                    ), modifier = Modifier.padding(8.dp), lineHeight = 20.sp
                )

            }

        }

    }
}