package com.example.gamearenacompose.data.remote

import com.example.gamearenacompose.data.remote.models.games.Game
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.remote.models.games.GameTrailerList
import com.example.gamearenacompose.data.remote.models.games.ScreenshotList
import com.example.gamearenacompose.data.remote.models.genre.Genre
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.ui.screens.games.GameViewModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RAWGApis {

    @GET("genres")
    suspend fun getGenres():GenreList

    @GET("genres/{id}")
    suspend fun getGenreDetails(
        @Path("id") id:Int
    ): Genre

    @GET("games")
    suspend fun getGames():GameList

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id:Int
    ):Game

    @GET("games/{id}/screenshots")
    suspend fun getGameScreenshots(
        @Path("id") id:Int
    ):ScreenshotList

    @GET("games")
    suspend fun getGamesPaginated(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("search") searchQuerry:String
    ): GameList

    @GET("genres")
    suspend fun getGenresPaginated(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("search") searchQuerry:String
    ): GenreList

    @GET("games/{id}/movies")
    suspend fun getGameTrailers(
        @Path("id") id:Int
    ):GameTrailerList


}