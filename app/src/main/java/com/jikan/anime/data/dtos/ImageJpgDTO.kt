package com.jikan.anime.data.dtos

import com.google.gson.annotations.SerializedName

data class ImageJpgDTO(
    @SerializedName("image_url") var imageUrl: String? = null,
    @SerializedName("small_image_url") var smallImageUrl: String? = null,
    @SerializedName("large_image_url") var largeImageUrl: String? = null
)
