package com.jikan.anime.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "animeDetail")
data class AnimeDetailEntity(

    @PrimaryKey(autoGenerate = true)
    var animeId: Int,

    var url: String? = null,
    var title: String? = null,
    var episodes: Int? = null,
    var rating: String? = null,
    var score: String? = null,
    var synopsis: String? = null,

    var imageUrl: String? = null,
    var smallImageUrl: String? = null,
    var largeImageUrl: String? = null,

    var youtubeId: String? = null,
    var embedUrl: String? = null,

    var currentPage: Int = 0,
)
