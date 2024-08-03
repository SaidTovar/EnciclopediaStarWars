package com.tovars.enciclopediastarwars.data.googleData

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GoogleRepository @Inject constructor(private val googleService: GoogleService) {

    suspend fun getImages(query: String): String {

        return withContext(Dispatchers.IO) {
            googleService.getImages(query)
        }

    }

}