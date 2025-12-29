package com.jikan.anime.data.local

import android.util.Log
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert


@Dao
interface AnimeDao {

    @Transaction
    @Query("select * from animeDetail ")
    fun animePagingSource(): PagingSource<Int, AnimeEntity>

   /* @Upsert
    suspend fun upsert(animes: List<AnimeEntity>)*/

    @Transaction
    suspend fun insertAnimeWithGenres(
        animes: List<AnimeEntity>
    ) {
        insertAnimeDetail(animes.map { it.animeDetail })
        insertRelatedGenres(
            animes.flatMap { it.genres.orEmpty() })
    }

    @Upsert
    suspend fun insertAnimeDetail(animeDetail: List<AnimeDetailEntity>)

    @Upsert
    suspend fun insertRelatedGenres(relatedGenres: List<GenresEntity>)

    @Query("DELETE FROM animeDetail")
    suspend fun clearAllAnime()

}