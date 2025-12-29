package com.jikan.anime.data.dtos

import com.google.gson.annotations.SerializedName


data class ImagesDTO(
    @SerializedName("jpg") var jpgImages: ImageJpgDTO? = null
)