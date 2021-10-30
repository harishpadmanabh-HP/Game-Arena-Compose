package com.example.gamearenacompose.data.repositoy

import com.example.gamearenacompose.data.remote.RAWGApis
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class GameRepository
@Inject constructor(private val api: RAWGApis) {

    suspend fun getGenres():Resource<GenreList> = try {
        Resource.Success(api.getGenres())
    }catch (e:Exception){
        Timber.e("Api error occured $e")
        Resource.Error("Error $e")
    }


}