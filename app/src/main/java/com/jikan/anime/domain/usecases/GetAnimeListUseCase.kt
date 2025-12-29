package com.jikan.anime.domain.usecases

import androidx.paging.PagingData
import com.jikan.anime.domain.models.Anime
import com.jikan.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeListUseCase
@Inject
constructor(
    private val repository: AnimeRepository
) {

    operator fun invoke(): Flow<PagingData<Anime>> {
        return repository.getAnimeList()
    }

}