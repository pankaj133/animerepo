package com.jikan.anime.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jikan.anime.R
import com.jikan.anime.domain.models.Anime

@Composable
fun AnimeItem(
    anime: Anime,
    onItemClick: (Anime) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(anime)
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            SubcomposeAsyncImage(
                model = anime.images?.jpgImages?.largeImageUrl,
                contentDescription = null,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .padding(horizontal = 10.dp)
                        )
                    }
                },
                error = {
                    Icon(
                        painter = painterResource(R.drawable.error_image),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = anime.title ?: "",
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text("Episodes: ${anime.episodes ?: "N/A"}")
                Text("⭐ Rating: ${anime.score ?: "N/A"}")
            }
        }
    }
}