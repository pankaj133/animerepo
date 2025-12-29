package com.jikan.anime.data.local

import androidx.compose.ui.input.key.type
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromGenresList(list: List<GenresEntity>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toGenresList(json: String?): List<GenresEntity>? {
        if (json == null) return null
        val type = object : TypeToken<List<GenresEntity>>() {}.type
        return gson.fromJson(json, type)
    }
}

@Database(
    entities = [GenresEntity::class, AnimeDetailEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AnimeDataBase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

}