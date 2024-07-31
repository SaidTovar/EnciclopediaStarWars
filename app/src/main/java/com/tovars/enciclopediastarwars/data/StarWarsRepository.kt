package com.tovars.enciclopediastarwars.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tovars.enciclopediastarwars.presentation.model.People
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarWarsRepository @Inject constructor(val apiService: StarWarsApiService) {

    fun getPeople(): Flow<PagingData<People>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = { PeoplePagingSource(apiService) }
        ).flow

    }

}