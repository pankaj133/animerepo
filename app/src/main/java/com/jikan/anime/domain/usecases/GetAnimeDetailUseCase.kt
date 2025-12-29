package com.jikan.anime.domain.usecases

import com.jikan.anime.domain.models.Anime
import com.jikan.anime.domain.repository.AnimeRepository
import com.jikan.anime.domain.utils.ApiResult
import javax.inject.Inject

class GetAnimeDetailUseCase
@Inject
constructor(val repository: AnimeRepository) {

    suspend operator fun invoke(animeId: Int): ApiResult<Anime?> {
        return repository.getAnimeDetail(animeId = animeId)
    }
}