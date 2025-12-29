package com.jikan.anime.data.mappers

import com.jikan.anime.data.dtos.AnimeDTO
import com.jikan.anime.data.dtos.GenresDTO
import com.jikan.anime.data.dtos.ImageJpgDTO
import com.jikan.anime.data.dtos.ImagesDTO
import com.jikan.anime.data.dtos.TrailerDTO
import com.jikan.anime.data.local.AnimeDetailEntity
import com.jikan.anime.data.local.AnimeEntity
import com.jikan.anime.data.local.GenresEntity
import com.jikan.anime.domain.models.Anime
import com.jikan.anime.domain.models.Genres
import com.jikan.anime.domain.models.ImageJpg
import com.jikan.anime.domain.models.Images
import com.jikan.anime.domain.models.Trailer


fun AnimeDTO.toDomain(): Anime {
    return Anime(
        animeId = this.animeId,
        url = this.url,
        title = this.title,
        rating = this.rating,
        episodes = this.episodes,
        images = this.images?.toDomain(),
        synopsis = this.synopsis,
        trailer = this.trailer?.toDomain(),
        genres = this.genres?.map { it.toDomain() }
    )
}

fun ImagesDTO.toDomain(): Images {
    return Images(
        jpgImages = this.jpgImages?.toDomain()
    )
}

fun ImageJpgDTO.toDomain(): ImageJpg {
    return ImageJpg(
        imageUrl = this.imageUrl,
        smallImageUrl = this.smallImageUrl,
        largeImageUrl = this.largeImageUrl
    )
}

fun TrailerDTO.toDomain(): Trailer {
    return Trailer(
        youtubeId = this.youtubeId,
        embedUrl = this.embedUrl,
    )
}

fun GenresDTO.toDomain(): Genres {
    return Genres(
        malId = this.malId,
        type = this.type,
        name = this.name,
        url = this.url
    )
}

fun GenresEntity.toDomain(): Genres {
    return Genres(
        malId = this.malId,
        type = this.type,
        name = this.name,
        url = this.url
    )
}


fun extractYouTubeVideoId(url: String?): String? {
    url?.let {
        val regex = Regex(
            "(?<=embed/|watch\\?v=|youtu.be/)[^?&\"'>]+"
        )
        return regex.find(url)?.value
    }
    return null
}


fun AnimeEntity.toDomain(): Anime {
    return Anime(
        animeId = this.animeDetail.animeId,
        url = this.animeDetail.url,
        title = this.animeDetail.title,
        rating = this.animeDetail.rating,
        episodes = this.animeDetail.episodes,
        score = this.animeDetail.score,
        images = Images(
            jpgImages = ImageJpg(
                imageUrl = this.animeDetail.imageUrl,
                smallImageUrl = this.animeDetail.smallImageUrl,
                largeImageUrl = this.animeDetail.largeImageUrl
            )
        ),
        synopsis = this.animeDetail.synopsis,
        videoId = extractYouTubeVideoId(this.animeDetail.embedUrl),
        trailer = Trailer(
            youtubeId = this.animeDetail.youtubeId,
            embedUrl = this.animeDetail.embedUrl,
        ),
        genres = this.genres?.map { it.toDomain() }
    )
}


fun AnimeDTO.toEntity(currentPage: Int): AnimeEntity {
    return AnimeEntity(
        animeDetail = AnimeDetailEntity(
            animeId = this.animeId,
            url = this.url,
            title = this.title,
            rating = this.rating,
            episodes = this.episodes,
            currentPage = currentPage,
            imageUrl = this.images?.jpgImages?.imageUrl,
            smallImageUrl = this.images?.jpgImages?.smallImageUrl,
            largeImageUrl = this.images?.jpgImages?.largeImageUrl,
            synopsis = this.synopsis,
            score = this.score,
            youtubeId = this.trailer?.youtubeId,
            embedUrl = this.trailer?.embedUrl
        ),
        genres = this.genres?.map { it.toGenresEntity(this.animeId) }
    )
}

fun GenresDTO.toGenresEntity(animeId: Int): GenresEntity {
    return GenresEntity(
        malId = this.malId,
        type = this.type,
        name = this.name,
        url = this.url,
        parentId = animeId
    )
}

