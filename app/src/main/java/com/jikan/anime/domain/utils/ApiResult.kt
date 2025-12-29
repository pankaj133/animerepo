package com.jikan.anime.domain.utils

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Failure(val error: ApiError) : ApiResult<Nothing>()
}