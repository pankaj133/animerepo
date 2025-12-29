package com.jikan.anime.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jikan.anime.ui.utils.NavigationItem

@Composable
fun ApplicationNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.AnimeList.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.AnimeList.route) {
            AnimeListScreen(navController = navController)
        }
        composable(NavigationItem.AnimeDetail.route) {
            AnimeDetailScreen(navController = navController)
        }
    }
}