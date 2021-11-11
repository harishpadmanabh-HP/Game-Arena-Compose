package com.example.gamearenacompose.data.repositoy

import com.example.gamearenacompose.data.remote.RAWGApis
import com.example.gamearenacompose.data.remote.models.games.Game
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.remote.models.games.GameTrailerList
import com.example.gamearenacompose.data.remote.models.games.ScreenshotList
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.utils.ApiCallStatus
import com.example.gamearenacompose.utils.ApiMapper
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

     suspend fun getGameDetails(id:Int):ApiMapper<Game> = try {
        val response = api.getGameDetails(id)
        ApiMapper(ApiCallStatus.SUCCESS,response,null)
    }catch (e:Exception){
        ApiMapper(ApiCallStatus.ERROR,null,e.toString())
    }

    suspend fun getGameScreenShots(id:Int):ApiMapper<ScreenshotList> = try {
        val response = api.getGameScreenshots(id)
        ApiMapper(ApiCallStatus.SUCCESS,response,null)
    }catch (e:Exception){
        ApiMapper(ApiCallStatus.ERROR,null,e.toString())
    }

    suspend fun getPaginatedGames(page:Int,pageSize:Int,search:String)=api.getGamesPaginated(page, pageSize,search)

    suspend fun getGameTrailers(id:Int):ApiMapper<GameTrailerList> = try {
        val response = api.getGameTrailers(id)
        ApiMapper(ApiCallStatus.SUCCESS,response,null)
    }catch (e:Exception){
        ApiMapper(ApiCallStatus.ERROR,null,e.toString())
    }

    suspend fun getPaginatedGenres(page:Int,pageSize:Int,search:String)  = api.getGenresPaginated(page, pageSize,search)
}