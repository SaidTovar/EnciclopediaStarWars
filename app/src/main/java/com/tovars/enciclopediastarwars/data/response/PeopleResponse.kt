package com.tovars.enciclopediastarwars.data.response

import com.google.gson.annotations.SerializedName
import com.tovars.enciclopediastarwars.presentation.model.People

data class PeopleResponse(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: String?,
    @SerializedName("mass") val mass: String?,
    @SerializedName("birth_year") val birth_year: String,
    @SerializedName("gender") val gender: String?,
    @SerializedName("homeworld") val homeworld: String,
    @SerializedName("films") val films: List<String>?,
    @SerializedName("species") val species: List<String>?,
    @SerializedName("vehicles") val vehicles: List<String>?,
    @SerializedName("starships") val starships: List<String>?
) {
    fun toPeople() = People(
        name = name,
        height = height,
        mass = mass,
        birth_year = birth_year,
        gender = gender,
        homeworld = homeworld,
        films = films,
        species = species,
        vehicles = vehicles,
        starships = starships
    )
}
