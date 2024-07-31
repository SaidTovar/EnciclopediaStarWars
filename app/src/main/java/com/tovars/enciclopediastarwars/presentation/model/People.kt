package com.tovars.enciclopediastarwars.presentation.model

import com.google.gson.annotations.SerializedName

data class People(
    val name: String,
    val height: String?,
    val mass: String?,
    val birth_year: String,
    val gender: String?,
    val homeworld: String,
    val films: List<String>?,
    val species: List<String>?,
    val vehicles: List<String>?,
    val starships: List<String>?
)