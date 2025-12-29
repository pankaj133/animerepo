package com.jikan.anime.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jikan.anime.domain.models.Anime
import com.jikan.anime.domain.usecases.GetAnimeListUseCase
import com.jikan.anime.ui.utils.NetworkConnectivityTracker
import com.jikan.anime.ui.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel
@Inject
constructor(
    useCase: GetAnimeListUseCase,
    networkConnectivityTracker: NetworkConnectivityTracker
) : ViewModel() {

    val animeList = useCase().cachedIn(viewModelScope)

    val networkStatus = networkConnectivityTracker.networkStatus
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NetworkStatus.Unavailable
        )


}
