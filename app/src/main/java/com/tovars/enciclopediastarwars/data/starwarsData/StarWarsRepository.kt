package com.tovars.enciclopediastarwars.data.starwarsData

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tovars.enciclopediastarwars.data.firebaseData.response.FirebaseService
import com.tovars.enciclopediastarwars.data.googleData.GoogleService
import com.tovars.enciclopediastarwars.presentation.model.People
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarWarsRepository @Inject constructor(
    val apiServiceStarWars: StarWarsApiService,
    val serviceGoogle: GoogleService,
    val firebaseService: FirebaseService
) {

    fun getPeople(): Flow<PagingData<People>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = { PeoplePagingSource(apiServiceStarWars, serviceGoogle, firebaseService) }
        ).flow

    }

}