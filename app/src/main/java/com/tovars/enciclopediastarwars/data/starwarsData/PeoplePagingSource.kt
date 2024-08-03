package com.tovars.enciclopediastarwars.data.starwarsData

import androidx.annotation.Keep
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tovars.enciclopediastarwars.data.firebaseData.response.FirebaseService
import com.tovars.enciclopediastarwars.data.firebaseData.response.LocalRepository
import com.tovars.enciclopediastarwars.data.googleData.GoogleService
import com.tovars.enciclopediastarwars.presentation.model.People
import java.io.IOException
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject

class PeoplePagingSource @Inject constructor(
    private val apiServiceStarWars: StarWarsApiService,
    private val serviceGoogle: GoogleService,
    private val firebaseService: FirebaseService
) : PagingSource<Int, People>() {

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {

        return try {

            val nextPage = params.key ?: 1


            val response = apiServiceStarWars.getPeople(nextPage)

            val peopleList = response.results

            val prevKey = if (nextPage == 1) null else nextPage - 1

            val nextKey = if (response.next != null) nextPage + 1 else null

            //val lock = ReentrantLock()

            val updatedPeopleList = peopleList.map { person ->
                // Usar un mutex para asegurar que solo una coroutine haga la llamada a la API a la vez

                //lock.lock()
                try {

                    var imageUrl = LocalRepository.imagesList.find { it.title == person.name }?.link ?: ""

                    if (imageUrl.isEmpty()) {

                        imageUrl = firebaseService.getImage(person.name)

                        if (imageUrl.isEmpty()) {

                            imageUrl = serviceGoogle
                                .getImages(query = person.name)

                            if (imageUrl.isNotEmpty()){

                                firebaseService.setImage(person.name, imageUrl)

                            }
                        }

                    }


                    person.toPeople().copy(url = imageUrl)
                    // Código que debe ser ejecutado por un solo hilo a la vez
                } finally {
                    //lock.unlock()
                }

            }

            LoadResult.Page(
                data = updatedPeopleList,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: IOException) {

            LoadResult.Error(e)

        }

    }

}