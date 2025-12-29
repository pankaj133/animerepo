package com.jikan.anime.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class AnimeEntity(
    @Embedded val animeDetail: AnimeDetailEntity,
    @Relation(
        parentColumn = "animeId",
        entityColumn = "parentId"
    )
    var genres: List<GenresEntity>? = null
)

