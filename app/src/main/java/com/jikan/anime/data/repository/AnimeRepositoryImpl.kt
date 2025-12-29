package com.jikan.anime.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jikan.anime.data.local.AnimeDataBase
import com.jikan.anime.data.local.AnimeEntity
import com.jikan.anime.data.mappers.toDomain
import com.jikan.anime.data.remote.AnimeService
import com.jikan.anime.data.remote.pager.AnimeRemoteMediator
import com.jikan.anime.domain.utils.ApiError
import com.jikan.anime.domain.models.Anime
import com.jikan.anime.domain.repository.AnimeRepository
import com.jikan.anime.domain.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject


class AnimeRepositoryImpl
@Inject constructor(
    val dataBase: AnimeDataBase,
    val animeService: AnimeService,
    val animeRemoteMediator: AnimeRemoteMediator
) : AnimeRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getAnimeList(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            remoteMediator = animeRemoteMediator,
            pagingSourceFactory = { dataBase.animeDao().animePagingSource() }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
    }

    override suspend fun getAnimeDetail(animeId: Int): ApiResult<Anime?> {
        return try {
            val response = animeService.getAnimeDetail(animeId)
            ApiResult.Success(response.body()?.toDomain())
        } catch (e: HttpException) {
            ApiResult.Failure(errorMapping(e))
        } catch (e: IOException) {
            ApiResult.Failure(ApiError.Network)
        } catch (e: Exception) {
            ApiResult.Failure(ApiError.Unknown(e.message ?: "Unknown error"))
        }
    }

    private fun errorMapping(e: HttpException): ApiError =
        when (e.code()) {
            401 -> ApiError.Unauthorized
            404 -> ApiError.NotFound
            in 500..599 -> ApiError.Server
            else -> ApiError.Unknown("HTTP ${e.code()}")
        }

}
