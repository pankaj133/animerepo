package com.jikan.anime.data.remote.pager

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jikan.anime.data.local.AnimeDataBase
import com.jikan.anime.data.local.AnimeEntity
import com.jikan.anime.data.mappers.toEntity
import com.jikan.anime.data.remote.AnimeService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator
@Inject
constructor(
    private val animeService: AnimeService,
    private val dataBase: AnimeDataBase
) : RemoteMediator<Int, AnimeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        lastItem.animeDetail.currentPage + 1
                    }
                }
            }
            try {
                val response = animeService.getAnimeList(loadKey, state.config.pageSize)
                val currentPage = response.body()?.pagination?.currentPage ?: 0
                dataBase.withTransaction {
                    if (response.isSuccessful) {
                        response.body()?.animeList?.let { data ->
                            dataBase.animeDao().apply {
                                insertAnimeWithGenres(data.map { it.toEntity(currentPage) })
                            }
                        }
                    } else {
                        MediatorResult.Success(
                            endOfPaginationReached = response.body()?.animeList?.isEmpty() ?: true
                        )
                    }
                }
                return MediatorResult.Success(
                    endOfPaginationReached = response.body()?.animeList?.isEmpty() ?: true
                )
            } catch (e: Exception) {
                return MediatorResult.Error(e)
            }

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}