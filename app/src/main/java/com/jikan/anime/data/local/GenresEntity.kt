package com.jikan.anime.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "genres",
    foreignKeys = [
        ForeignKey(
            entity = AnimeDetailEntity::class,
            parentColumns = ["animeId"],
            childColumns = ["parentId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("parentId")]
)
data class GenresEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    var malId: Int? = null,
    var type: String? = null,
    var name: String? = null,
    var url: String? = null,

    val parentId: Int //foreignKey
)