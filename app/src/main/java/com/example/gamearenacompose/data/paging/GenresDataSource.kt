package com.example.gamearenacompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gamearenacompose.data.remote.models.genre.GenreList
import com.example.gamearenacompose.data.repositoy.GameRepository

class GenresDataSource(
    private val repo: GameRepository,
    private var search: String = ""
) : PagingSource<Int, GenreList.Result>() {
    override fun getRefreshKey(state: PagingState<Int, GenreList.Result>): Int? {
        return state.anchorPosition

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GenreList.Result> {
        val nextPage = params.key ?: 1
        val response = repo.getPaginatedGenres(nextPage, 50, search)
        return if (response.results.isEmpty()) {
            LoadResult.Error(Throwable("No Games Found"))
        } else {
            LoadResult.Page(
                data = response.results,
                prevKey =
                if (nextPage == 1) null
                else nextPage - 1,
                nextKey = if(response.next.isNullOrBlank()) null else nextPage.plus(1)
            )
        }
    }
}