package com.jikan.anime.domain.utils

sealed class ApiError {
    object Network : ApiError()
    object Unauthorized : ApiError()
    object NotFound : ApiError()
    object Server : ApiError()
    data class Unknown(val msg: String) : ApiError()
}