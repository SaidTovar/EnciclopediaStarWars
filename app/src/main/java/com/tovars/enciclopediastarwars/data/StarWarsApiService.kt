package com.tovars.enciclopediastarwars.data

import com.tovars.enciclopediastarwars.data.response.WrapperPeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApiService {

    @GET("people/")
    suspend fun getPeople(@Query("page") page: Int): WrapperPeopleResponse

/*
    @GET("planets/")
    suspend fun getPlanets(@Query("page") page: Int): PlanetsResponse

    @GET("starships/")
    suspend fun getStarships(@Query("page") page: Int): StarshipsResponse

    @GET("films/")
    suspend fun getFilms(@Query("page") page: Int): FilmsResponse

    @GET("vehicles/")
    suspend fun getVehicles(@Query("page") page: Int): VehiclesResponse

    @GET("species/")
    suspend fun getSpecies(@Query("page") page: Int): SpeciesResponse
*/

}