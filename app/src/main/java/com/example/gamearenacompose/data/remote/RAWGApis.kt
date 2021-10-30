package com.example.gamearenacompose.data.remote

import com.example.gamearenacompose.data.remote.models.games.Game
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.remote.models.genre.Genre
import com.example.gamearenacompose.data.remote.models.genre.GenreList
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



}