package com.jikan.anime.ui.di

import android.content.Context
import com.jikan.anime.ui.utils.NetworkConnectivityTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    @Singleton
    @Provides
    fun provideNetworkObserver(@ApplicationContext context: Context) = NetworkConnectivityTracker(
        context
    )
}