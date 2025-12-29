package com.jikan.anime.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Anime(
    var animeId: Int? = null,
    var url: String? = null,
    var title: String? = null,
    var episodes: Int? = null,
    var rating: String? = null,
    var images: Images? = null,
    var synopsis: String? = null,
    var trailer: Trailer? = null,
    var score: String? = null,
    var videoId:String?=null,
    var genres: List<Genres>? = null
): Parcelable


