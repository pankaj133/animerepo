package com.jikan.anime.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Genres(
    var malId: Int? = null,
    var type: String? = null,
    var name: String? = null,
    var url: String? = null
) : Parcelable