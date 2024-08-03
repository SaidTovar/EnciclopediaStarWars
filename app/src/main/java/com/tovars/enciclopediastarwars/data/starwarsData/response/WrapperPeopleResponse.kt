package com.tovars.enciclopediastarwars.data.starwarsData.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WrapperPeopleResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PeopleResponse>
)
