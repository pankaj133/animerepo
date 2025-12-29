package com.jikan.anime.data.dtos

import com.google.gson.annotations.SerializedName

data class PaginationItemDTO(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("per_page") var perPage: Int? = null
)
