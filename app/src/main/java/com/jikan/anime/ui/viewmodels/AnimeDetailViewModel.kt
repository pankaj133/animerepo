package com.jikan.anime.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jikan.anime.ui.utils.NetworkConnectivityTracker
import com.jikan.anime.ui.utils.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel
@Inject
constructor(
    networkConnectivityTracker: NetworkConnectivityTracker
) : ViewModel() {

    val networkStatus = networkConnectivityTracker.networkStatus
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NetworkStatus.Unavailable
        )


}
