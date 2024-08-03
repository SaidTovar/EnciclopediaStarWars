package com.tovars.enciclopediastarwars.data.googleData.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("link") val link: String,
    @SerializedName("title") val title: String
)