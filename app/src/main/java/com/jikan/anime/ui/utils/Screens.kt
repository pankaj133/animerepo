package com.jikan.anime.ui.utils

enum class Screen {
    ANIME_LIST,
    ANIME_DETAIL,
}

sealed class NavigationItem(val route: String) {
    object AnimeList : NavigationItem(Screen.ANIME_LIST.name)
    object AnimeDetail : NavigationItem(Screen.ANIME_DETAIL.name)
}
