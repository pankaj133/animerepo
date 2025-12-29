package com.jikan.anime.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Trailer(
    var youtubeId: String? = null,
    var embedUrl: String? = null,
): Parcelable