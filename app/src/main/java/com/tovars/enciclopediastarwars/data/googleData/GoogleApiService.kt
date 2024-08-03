package com.tovars.enciclopediastarwars.data.googleData

import androidx.annotation.Keep
import com.tovars.enciclopediastarwars.data.googleData.response.WrapperImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

@Keep
interface GoogleApiService {

    @GET("v1")
    suspend fun getImages(
        @Query("key") key: String,
        @Query("cx") cx: String,
        @Query("q") query: String,
        @Query("searchType") searchType: String
    ): WrapperImagesResponse

}