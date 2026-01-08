package com.jikan.anime.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.jikan.anime.ui.utils.NavigationItem
import com.jikan.anime.ui.utils.NetworkStatus
import com.jikan.anime.ui.viewmodels.AnimeListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    viewModel: AnimeListViewModel = hiltViewModel(),
    navController: NavController
) {
    val animeLazyPagingList = viewModel.animeList.collectAsLazyPagingItems()

    val netWorkState by viewModel.networkStatus.collectAsState()

    LaunchedEffect(netWorkState) {
        when (netWorkState) {
            NetworkStatus.Available -> {
                animeLazyPagingList.retry()
            }

            NetworkStatus.Unavailable -> {
                //  animeLazyPagingList.refresh()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top Anime") }
            )
        }
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Log.i("pankaj", "AnimeListScreen: ")
            if (animeLazyPagingList.itemCount > 0) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(animeLazyPagingList.itemCount) { index ->

                        animeLazyPagingList[index]?.let { anime ->
                            AnimeItem(anime = anime) {

                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("anime", anime)

                                navController.navigate(
                                    NavigationItem.AnimeDetail.route,
                                )

                            }
                        }
                    }
                    if (animeLazyPagingList.loadState.append is LoadState.Loading) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                LinearProgressIndicator()
                            }
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Anime available",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
