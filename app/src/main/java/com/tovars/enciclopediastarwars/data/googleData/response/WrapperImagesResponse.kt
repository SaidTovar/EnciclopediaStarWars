package com.tovars.enciclopediastarwars.data.googleData.response

import com.google.gson.annotations.SerializedName

data class WrapperImagesResponse(

    @SerializedName("items") val items: List<ImageResponse>

)