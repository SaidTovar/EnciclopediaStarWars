package com.tovars.enciclopediastarwars.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tovars.enciclopediastarwars.presentation.model.People
import java.io.IOException
import javax.inject.Inject

class PeoplePagingSource @Inject constructor(private val apiService: StarWarsApiService) : PagingSource<Int, People>() {
    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {

        return try {

            val nextPage = params.key ?: 1

            val response = apiService.getPeople(nextPage)

            val peopleList = response.results

            val prevKey = if (nextPage == 1) null else nextPage - 1

            val nextKey = if (response.next != null) nextPage + 1 else null


            LoadResult.Page(
                data = peopleList.map { it.toPeople() },
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: IOException) {

            LoadResult.Error(e)

        }

    }


}