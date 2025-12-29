package com.jikan.anime.data.remote

import com.jikan.anime.data.dtos.AnimeDTO
import com.jikan.anime.data.dtos.AnimeListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET(Urls.TOP_ANIME)
    suspend fun getAnimeList(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<AnimeListDTO?>

    @GET(Urls.ANIME_DETAIL)
    suspend fun getAnimeDetail(@Path("anime_id") animId: Int): Response<AnimeDTO?>

}