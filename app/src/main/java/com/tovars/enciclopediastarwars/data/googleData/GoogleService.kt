package com.tovars.enciclopediastarwars.data.googleData

import androidx.annotation.Keep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Keep
class GoogleService @Inject constructor(private val apiServiceGoogle: GoogleApiService) {

    @Keep
    suspend fun getImages(query: String): String {

        return withContext(Dispatchers.IO) {

            try {

                apiServiceGoogle
                    .getImages(
                        key = NetworkConfig.GOOGLE_APIKEY,
                        cx = NetworkConfig.GOOGLE_CX,
                        searchType = NetworkConfig.GOOGLE_SEARCH_TYPE,
                        query = query
                    ).items.firstOrNull()?.link ?: ""

            } catch (e: Exception) {
                ""
            }


        }

    }

}