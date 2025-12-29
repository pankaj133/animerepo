package com.jikan.anime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.jikan.anime.R
import com.jikan.anime.domain.models.Anime
import com.jikan.anime.ui.utils.NetworkStatus
import com.jikan.anime.ui.viewmodels.AnimeDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    navController: NavController,
    viewModel: AnimeDetailViewModel = hiltViewModel()
) {
    val anime = navController.previousBackStackEntry?.savedStateHandle?.get<Anime>("anime")
    val netWorkState by viewModel.networkStatus.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime Detail") })
        }) { padding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = Color.Transparent)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    when (netWorkState) {
                        NetworkStatus.Available -> {
                            VideoAndImageSection(
                                videoId = anime?.videoId,
                                imageUrl = anime?.images?.jpgImages?.largeImageUrl ?: ""
                            )
                        }

                        NetworkStatus.Unavailable -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.error_image),
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(100.dp)
                                )
                            }
                        }
                    }

                }
                item {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = anime?.title ?: "",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text("⭐ ${anime?.score}", color = Color.Black)
                            Text("Episodes: ${anime?.episodes}", color = Color.Black)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        GenreChips(anime?.genres ?: emptyList())

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Synopsis",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = anime?.synopsis ?: "synopsis",
                            color = Color.Black,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }
    }
}

