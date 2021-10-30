package com.example.gamearenacompose.ui.screens.home

import android.icu.number.Scale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.gamearenacompose.R
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.ui.theme.darkBLue
import com.example.gamearenacompose.ui.theme.grey
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {
    viewModel.getGenres()
    val genres by remember {
        viewModel.genres
    }

    val isLoading by remember {
        viewModel.isLoading
    }

    val error by remember {
        viewModel.error
    }

    Surface(
        color = darkBLue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
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
            if (!genres?.isEmpty()) {
                GenreView(
                    genres, isLoading, error, Modifier.layoutId("genre_view")
                )
            }

        }


    }

}

@Composable
fun GenreView(
    genres: List<GenreList.Result>,
    isLoading: Boolean,
    error: String, modifier: Modifier
) {
    Column {
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
        )

        LazyRow() {
            items(items = genres,
                itemContent = {
                    GenreViewItem(genre = it)
                }
            )
        }

    }

}

@ExperimentalCoilApi
@Composable
fun GenreViewItem(genre: GenreList.Result, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(top=20.dp,end = 12.dp)
    ){
        Box(modifier = Modifier.shadow(10.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color.LightGray, grey)
                )
            )
        ){
            Image(
                painter = rememberImagePainter(genre.imageBackground),
                contentDescription = null,
                modifier = Modifier.size(150.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        }
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