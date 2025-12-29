package com.jikan.anime.data.di

import android.content.Context
import androidx.room.Room
import com.jikan.anime.data.local.AnimeDataBase
import com.jikan.anime.data.remote.AnimeService
import com.jikan.anime.data.remote.Urls
import com.jikan.anime.data.remote.pager.AnimeRemoteMediator
import com.jikan.anime.data.repository.AnimeRepositoryImpl
import com.jikan.anime.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Singleton
    @Provides
    fun provideHttpLogging() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideAnimeService(retrofit: Retrofit): AnimeService = retrofit.create(AnimeService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
        }.build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
        }.build()


    @Singleton
    @Provides
    fun provideAnimeRepository(
        animeService: AnimeService,
        dataBase: AnimeDataBase,
        animeRemoteMediator: AnimeRemoteMediator
    ): AnimeRepository {
        return AnimeRepositoryImpl(
            dataBase = dataBase,
            animeService = animeService,
            animeRemoteMediator = animeRemoteMediator
        )
    }

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = AnimeDataBase::class.java,
        name = "animeDb"
    ).build()


    @Singleton
    @Provides
    fun provideAnimeRemoteMediator(
        animeService: AnimeService,
        dataBase: AnimeDataBase
    ) = AnimeRemoteMediator(
        animeService = animeService,
        dataBase = dataBase
    )
}