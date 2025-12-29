package com.jikan.anime.data.dtos

import com.google.gson.annotations.SerializedName

data class AnimeListDTO(
    @SerializedName("pagination") val pagination: PaginationDTO? = null,
    @SerializedName("data") val animeList: List<AnimeDTO>? = null
)
