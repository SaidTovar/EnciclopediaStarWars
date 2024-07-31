package com.tovars.enciclopediastarwars.data.response

import com.google.gson.annotations.SerializedName

data class WrapperPeopleResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PeopleResponse>
)
