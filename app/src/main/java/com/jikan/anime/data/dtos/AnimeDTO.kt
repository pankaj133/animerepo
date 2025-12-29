package com.jikan.anime.data.dtos

import com.google.gson.annotations.SerializedName

data class AnimeDTO(
    @SerializedName("mal_id") var animeId: Int,
    @SerializedName("url") var url: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("episodes") var episodes: Int? = null,
    @SerializedName("rating") var rating: String? = null,
    @SerializedName("score") var score: String? = null,
    @SerializedName("synopsis") var synopsis: String? = null,
    @SerializedName("images") var images: ImagesDTO? = null,
    @SerializedName("trailer") var trailer: TrailerDTO? = null,
    @SerializedName("genres") var genres: List<GenresDTO>? = null
)
