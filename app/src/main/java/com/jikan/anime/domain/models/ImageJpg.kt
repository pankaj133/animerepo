package com.jikan.anime.domain.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable


@Parcelize
data class ImageJpg(
    var imageUrl: String? = null,
    var smallImageUrl: String? = null,
    var largeImageUrl: String? = null
): Parcelable
