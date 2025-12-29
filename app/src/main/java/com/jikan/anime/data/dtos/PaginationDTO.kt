package com.jikan.anime.data.dtos

import com.google.gson.annotations.SerializedName

data class PaginationDTO(
    @SerializedName("last_visible_page") var lastVisiblePage: Int? = null,
    @SerializedName("has_next_page") var hasNextPage: Boolean? = null,
    @SerializedName("current_page") var currentPage: Int? = null,
    @SerializedName("items") var paginationItem: PaginationItemDTO? = null
)
