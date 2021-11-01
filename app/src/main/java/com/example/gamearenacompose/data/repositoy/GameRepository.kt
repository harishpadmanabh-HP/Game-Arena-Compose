package com.example.gamearenacompose.data.repositoy

import com.example.gamearenacompose.data.remote.RAWGApis
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class GameRepository
@Inject constructor(private val api: RAWGApis) {

    suspend fun getGenres():Resource<GenreList> = try {
        Resource.Success(api.getGenres())
    }catch (e:Exception){
        Resource.Error("Error $e")
    }

    suspend fun getAllGames():Resource<GameList> = try {
        Resource.Success(api.getGames())
    }catch (e:Exception){
        Resource.Error("Error $e")
    }



}