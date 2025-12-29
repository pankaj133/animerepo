package com.jikan.anime.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Images(
    var jpgImages: ImageJpg? = null,
) : Parcelable

