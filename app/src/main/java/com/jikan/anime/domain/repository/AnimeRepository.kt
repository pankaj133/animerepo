package com.jikan.anime.domain.repository

import androidx.paging.PagingData
import com.jikan.anime.domain.models.Anime
import com.jikan.anime.domain.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    fun getAnimeList(): Flow<PagingData<Anime>>

    suspend fun getAnimeDetail(animeId: Int): ApiResult<Anime?>
}