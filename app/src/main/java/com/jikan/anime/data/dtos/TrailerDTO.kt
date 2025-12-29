package com.jikan.anime.data.dtos

import android.provider.MediaStore
import com.google.gson.annotations.SerializedName


data class TrailerDTO(
    @SerializedName("youtube_id") var youtubeId: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("embed_url") var embedUrl: String? = null,
    @SerializedName("images") var images: ImagesDTO? = null
)