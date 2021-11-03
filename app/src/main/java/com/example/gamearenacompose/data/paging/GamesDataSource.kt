package com.example.gamearenacompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gamearenacompose.data.remote.RAWGApis
import com.example.gamearenacompose.data.remote.models.games.GameList
import com.example.gamearenacompose.data.repositoy.GameRepository
import javax.inject.Inject

class GamesDataSource(
private val repo:GameRepository,
private var search:String =""
) : PagingSource<Int, GameList.Result>() {

    @Inject
    lateinit var api:RAWGApis


    override fun getRefreshKey(state: PagingState<Int, GameList.Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameList.Result> {
        val nextPage = params.key ?: 1
        val response = repo.getPaginatedGames(nextPage,50,search)
        return if(response.results.isEmpty()){
            LoadResult.Error(Throwable("No Games Found"))
        }else{
            LoadResult.Page(
                data = response.results,
                prevKey =
                if (nextPage == 1) null
                else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        }


    }


}